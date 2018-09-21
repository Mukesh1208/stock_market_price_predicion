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
         			},{
         				header : 'Sentiment Discrepency Index',
         				dataIndex : 'sentimentDescIndex',
         				sortable:true,
         				width    :250
         			},
         			{
         				header : 'Daily Turn Over',
         				dataIndex : 'dailyTurnOver',
         				sortable:true,
         				width    :250
         			},{
         				header : 'Prediction',
         				dataIndex : 'predictionValue',
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
		          {name:'price',mapping:'price',type:'double'},
		          {name:'volume',mapping:'volume',type:'double'},
		          {name:'sentimentDescIndex',mapping:'sentimentDescIndex',type:'double'},
		          {name:'marketIndex',mapping:'marketIndex',type:'double'},
		          {name:'dailyTurnOver',mapping:'dailyTurnOver',type:'double'},
		          {name:'predictionValue',mapping:'predictionValue',type:'double'}
		          ]
		
	});

	keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/review/viewPrediction.do',
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
	
	
	Ext.create('Ext.chart.Chart', {
	    renderTo: 'content',
	    width: 500,
	    height: 300,
	    animate: true,
	    store: keyStore,
	    axes: [{
	        type: 'Numeric',
	        position: 'bottom',
	        fields: ['predictionValue'],
	        title: 'Prediction Value',
	        grid: true,
	        minimum: 0
	    }, {
	        type: 'Category',
	        position: 'left',
	        fields: ['company'],
	        title: 'Company ID'
	    }],
	    series: [{
	        type: 'bar',
	        axis: 'bottom',
	        highlight: true,
	        tips: {
	          trackMouse: true,
	          width: 140,
	          height: 28,
	          renderer: function(storeItem, item) {
	            this.setTitle(storeItem.get('company') + ': ' + storeItem.get('predictionValue'));
	          }
	        },
	        label: {
	          display: 'insideEnd',
	            field: 'rating',
	            renderer: Ext.util.Format.numberRenderer('0'),
	            orientation: 'horizontal',
	            color: '#333',
	            'text-anchor': 'middle'
	        },
	        xField: 'company',
	        yField: 'predictionValue'
	    }]
	});
	
	


});
	
	
	
