Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.form.*', 'Ext.layout.container.Column', 'Ext.tab.Panel']);
Ext.Loader.setConfig({
    enabled: true
});
Ext.tip.QuickTipManager.init();



var keycolumns=[
         			{
         				header : 'Product ID',
         				dataIndex : 'productId',
         				sortable:true,
         				width:80
         			},
         			{
         				header : 'Product Type',
         				dataIndex : 'productType',
         				sortable:true,
         				width:80
         			},
         			{
         				header : 'Positive Rating',
         				dataIndex : 'positiveRating',
         				sortable:true,
         				width    :80
         			},
         			{
         				header : 'Negative Rating',
         				dataIndex : 'negativeRating',
         				sortable:true,
         				width    :80
         			},
         			{
         				header : 'Neutral Rating',
         				dataIndex : 'neutralRating',
         				sortable:true,
         				width    :80
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
	
	var typeStore;
	
Ext.onReady(function () {

	var loadMask = new Ext.LoadMask(Ext.getBody(), {msg:"Loading"});
	loadMask.show();
	
	Ext.define('keywordModel',{
		extend : 'Ext.data.Model',
		fields : [ 
		          {name:'productId',mapping:'productId',type:'int'},
		          {name:'productType',mapping:'productType',type:'int'},
		          {name:'positiveRating',mapping:'positiveRating',type:'int'},
		          {name:'negativeRating',mapping:'negativeRating',type:'int'},
		          {name:'neutralRating',mapping:'neutralRating',type:'int'},
		          {name:'featureType',mapping:'featureType',type:'int'}
		          ]
		
	});

	keyStore = Ext.create('Ext.data.Store', {
		id : 'keyStoreId',
		name : 'keyStoreName',
		model : 'keywordModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/review/viewTotalPolarity.do',
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
				showConfirmationMsg("Polarity Computation Could not be Done");
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
	        fields: ['positiveRating'],
	        label: {
	            renderer: Ext.util.Format.numberRenderer('0,0')
	        },
	        title: 'Positive Rating',
	        grid: true,
	        minimum: 0
	    }, {
	        type: 'Category',
	        position: 'left',
	        fields: ['productId'],
	        title: 'Product Id'
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
	            this.setTitle(storeItem.get('productId') + ': ' + storeItem.get('positiveRating') + ' Positive Rating');
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
	        xField: 'productId',
	        yField: 'positiveRating'
	    }]
	});
	
	
	Ext.create('Ext.chart.Chart', {
	    renderTo: 'negativecontent',
	    width: 500,
	    height: 300,
	    animate: true,
	    store: keyStore,
	    axes: [{
	        type: 'Numeric',
	        position: 'bottom',
	        fields: ['negativeRating'],
	        label: {
	            renderer: Ext.util.Format.numberRenderer('0,0')
	        },
	        title: 'Negative Rating',
	        grid: true,
	        minimum: 0
	    }, {
	        type: 'Category',
	        position: 'left',
	        fields: ['productId'],
	        title: 'Product Id'
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
	            this.setTitle(storeItem.get('productId') + ': ' + storeItem.get('negativeRating') + ' Negative Rating');
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
	        xField: 'productId',
	        yField: 'negativeRating'
	    }]
	});
	
	      
		   

			Ext.create('Ext.chart.Chart', {
			    renderTo: 'neutralcontent',
			    width: 500,
			    height: 300,
			    animate: true,
			    store: keyStore,
			    axes: [{
			        type: 'Numeric',
			        position: 'bottom',
			        fields: ['neutralRating'],
			        label: {
			            renderer: Ext.util.Format.numberRenderer('0,0')
			        },
			        title: 'Neutral Rating',
			        grid: true,
			        minimum: 0
			    }, {
			        type: 'Category',
			        position: 'left',
			        fields: ['productId'],
			        title: 'Product Id'
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
			            this.setTitle(storeItem.get('productId') + ': ' + storeItem.get('neutralRating') + ' Neutral Rating');
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
			        xField: 'productId',
			        yField: 'neutralRating'
			    }]
			});	
	

});
	
	
	
