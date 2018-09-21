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
         				header : 'Volume',
         				dataIndex : 'volume',
         				sortable:true,
         				width    :180
         			},
         			{
         				header : 'Price',
         				dataIndex : 'price',
         				sortable:true,
         				width    :180
         			},
         			{
         				header : 'Market Index',
         				dataIndex : 'marketIndex',
         				sortable:true,
         				width    :180
         			},
         			{
         				header : 'Daily Turn Over',
         				dataIndex : 'dailyTurnOver',
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
		          {name:'price',mapping:'price',type:'double'},
		          {name:'volume',mapping:'volume',type:'double'},
		          {name:'marketIndex',mapping:'marketIndex',type:'double'},
		          {name:'dailyTurnOver',mapping:'dailyTurnOver',type:'double'}
		          ]
		
	});

	keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/review/viewYahoo.do',
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
				showConfirmationMsg("Finance Could not be Found");
			}
						
				loadMask.hide();
			}
		},
		autoLoad : true
	});
	
	
	
	
	
	var keyGrid = Ext.create('Ext.grid.Panel', {
		title:'Finance Output',
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
	
	
	
