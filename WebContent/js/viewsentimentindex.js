Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.form.*', 'Ext.layout.container.Column', 'Ext.tab.Panel']);
Ext.Loader.setConfig({
    enabled: true
});
Ext.tip.QuickTipManager.init();



var keycolumns=[
         			{
         				header : 'Company ID',
         				dataIndex : 'companyId',
         				sortable:true,
         				width:150
         			},
					{
         				header : 'Company',
         				dataIndex : 'company',
         				sortable:true,
         				width:180
         			},
         			{
         				header : 'Positive Rating',
         				dataIndex : 'positiveRating',
         				sortable:true,
         				width    :180
         			},
         			{
         				header : 'Negative Rating',
         				dataIndex : 'negativeRating',
         				sortable:true,
         				width    :180
         			},
         			{
         				header : 'Sentiment Index',
         				dataIndex : 'sentimentIndex',
         				sortable:true,
         				width    :180
         			},{
         				header : 'Sentiment Discrepency Index',
         				dataIndex : 'sentimentDescIndex',
         				sortable:true,
         				width    :250
         			}];

var hideConfirmationMsg;
var showConfirmationMsg;
/* Hide the Confirmation Message */
	hideConfirmationMsg = function() {
		var confMsgDiv = Ext.get('confirmationMessage');
		confMsgDiv.dom.innerHTML = "";
		confMsgDiv.dom.style.display = 'none';
	}
	/* Show Confirmation Message */
	showConfirmationMsg = function(msg) {
		var confMsgDiv = Ext.get('confirmationMessage');
		confMsgDiv.dom.innerHTML =  msg;
		confMsgDiv.dom.style.display = 'inline-block';		
	}
	var keyStore;
Ext.onReady(function () {

	var loadMask = new Ext.LoadMask(Ext.getBody(), {msg:"Loading"});
	loadMask.show();
	
	Ext.define('keywordModel',{
		extend : 'Ext.data.Model',
		fields : [ 
		          {name:'companyId',mapping:'companyId',type:'int'},
		          {name:'company',mapping:'company',type:'string'},
		          {name:'positiveRating',mapping:'positiveRating',type:'int'},
		          {name:'negativeRating',mapping:'negativeRating',type:'int'},
		          {name:'sentimentDescIndex',mapping:'sentimentDescIndex',type:'double'},
		          {name:'sentimentIndex',mapping:'sentimentIndex',type:'double'}
		          ]
		
	});

	keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/review/viewSentimentIndex.do',
			extraParams:{
				type:'1'
			},
			actionMethods:{
				read:'POST'
			},
			reader : {
				type :'json',
				root:'model'
			}
		},
		listeners:
		{
			'load':function(store, records){
			
			var count=store.getCount();
			if(count<=0)
			{
				showConfirmationMsg("Sentiment Index Could not be Found");
			}
						
				loadMask.hide();
			}
		},
		autoLoad : true
	});
	
	
	
	
	
	var keyGrid = Ext.create('Ext.grid.Panel', {
		title:'Sentiment Index Output',
		forceFit : true,
		id : 'keyGrid',
		store : keyStore,
		columns : keycolumns,
		width:1200,
		height:300,
		autoFit : true,
		autoscroll:true,
		stripRows:true,
		renderTo : 'keyContainer',
		collapsible:true,
		overflowY:'auto'
	});

});
	
	
	
