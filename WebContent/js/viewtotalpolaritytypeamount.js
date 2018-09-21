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

var productColumns =[
{
		header : 'Company ID',
		dataIndex : 'companyId',
		sortable:true,
		width:80
	},
	{
		header : 'Company Name',
		dataIndex : 'companyName',
		sortable:true,
		width:80
	},
                     
                     ];

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
	
	
	Ext.define('productModel', {
		extend : 'Ext.data.Model',
		fields : [ 
		           {name:'companyId', mapping:'companyId',type:'int'},
		           {name:'companyName', mapping:'companyName',type:'string'}
				 ],
		idProperty: 'companyId'
	});
	
	
	var genericStore = Ext.create('Ext.data.Store', {
		id : 'genericStoreId',
		name : 'genericStoreName',
		model : 'productModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/review/retriveAllCompaniesForCompanyType.do',
			extraParams:{
			 	type:1
				},
			actionMethods:{
				read:'POST'
			},
			reader : {
				type :'json'
			}
			
		},
		listeners:
		{
			'load':function(store, records){
		}			
		},
		autoLoad : true
	});
	genericStore.load();
	
	
	var reviewGrid = Ext.create('Ext.grid.Panel', {
		collapsible:true,
		title:'Company Information',
		forceFit : true,
		id : 'reviewGrid',
		store : genericStore,
		columns :productColumns,
		height : 200,
		width : 500,
		autoFit : true,
		stripRows:true,
		renderTo : 'reviewGridContainer'
	});

	var loadMask = new Ext.LoadMask(Ext.getBody(), {msg:"Loading"});
	loadMask.show();
	
	Ext.define('keywordModel',{
		extend : 'Ext.data.Model',
		fields : [ 
		          {name:'companyId',mapping:'companyId',type:'int'},
		          {name:'companyType',mapping:'companyType',type:'int'},
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
			url :contextPath+'/review/viewTotalPolarityForType.do',
			extraParams:{
				type:'AMOUNT'
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
	        fields: ['companyId'],
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
	            this.setTitle(storeItem.get('companyId') + ': ' + storeItem.get('positiveRating') + ' Positive Rating');
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
	        xField: 'collegeId',
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
	        fields: ['companyId'],
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
	            this.setTitle(storeItem.get('companyId') + ': ' + storeItem.get('negativeRating') + ' Negative Rating');
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
	        xField: 'collegeId',
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
			        fields: ['companyId'],
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
			            this.setTitle(storeItem.get('companyId') + ': ' + storeItem.get('neutralRating') + ' Neutral Rating');
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
			        xField: 'companyId',
			        yField: 'neutralRating'
			    }]
			});	
	

});
	
	
	
