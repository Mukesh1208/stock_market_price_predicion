Ext.require( [ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);

Ext.Loader.setConfig( {
	enabled : true
});

var hideConfirmationMsg;
var showConfirmationMsg;
var loadMask;
var contentPanel;
/* Hide the Confirmation Message */
hideConfirmationMsg = function() {
	var confMsgDiv = Ext.get('confirmationMessage');
	confMsgDiv.dom.innerHTML = "";
	confMsgDiv.dom.style.display = 'none';
}
/* Show Confirmation Message */
showConfirmationMsg = function(msg) {
	var confMsgDiv = Ext.get('confirmationMessage');
	confMsgDiv.dom.innerHTML = msg;
	confMsgDiv.dom.style.display = 'inline-block';
}
function generateJSONRequestForAddingReview()
{
	var reviewGen={};
	var collegeid=Ext.getCmp('catid').getValue();
	if(collegeid!=null)
	{
		reviewGen.collegeid=collegeid;
	}
	var producttype=Ext.getCmp('collegeid').getValue();
	if(producttype!=null)
	{
		reviewGen.producttype=producttype;
	}
	var webUrlType=Ext.getCmp('webUrlType').getValue();
	if(webUrlType!=null)
	{
		reviewGen.webUrlType=webUrlType;
	}
	var webUrl=Ext.getCmp('webUrl').getValue();
	if(webUrl!=null)
	{
		reviewGen.webUrl=webUrl;
	}
	
	return reviewGen;
}


function doReviewGenerationRequest(reviewGen, urlLink)
{
loadMask = new Ext.LoadMask(Ext.getBody(), {msg:"Loading"});
loadMask.show();
Ext.Ajax.request({	
method: 'POST',
processData: false,
contentType:'application/json',
jsonData: Ext.encode(reviewGen),
url:urlLink, 
success: function(response) {
var data;
if (response){
			 
			var JsonData = Ext.decode(response.responseText);
				if(JsonData.ebErrors != null){
					var errorObj=JsonData.ebErrors;
					for(i=0;i<errorObj.length;i++)
					{
							var value=errorObj[i].errMessage;
							showConfirmationMsg(value);
					}
					loadMask.hide();
				}
				else
				{
					var value=JsonData.message;
					showConfirmationMsg(value);
					contentPanel.hide();
					loadMask.hide();
				}
			}
},
failure : function(data) {
loadMask.hide();
}
});
}



Ext
		.onReady(function() {
			
			var webUrlTypeStore=Ext.create('Ext.data.Store',{
				fields: ['webUrlTypeId', 'webUrlType'],
				data:[{
						"webUrlTypeId": "AMAZON",
			            "webUrlType": "Amazon"
			           },
			           {
			        	   "webUrlTypeId": "FLIPKART",
			        	   "webUrlType": "Flipkart"
			           }
				]
				
			});
			
			Ext.define('productTypeModel', {
				extend : 'Ext.data.Model',
				fields : [ 
				           {name:'collegeTypeId', mapping:'collegeTypeId',type:'int'},
				           {name:'collegeName', mapping:'collegeName',type:'string'}
						 ],
				idProperty: 'collegeTypeId'
			});
			
			
			var productTypeStore = Ext.create('Ext.data.Store', {
				id : 'productTypeStoreId',
				name : 'productTypeStoreName',
				model : 'productTypeModel',
				proxy : {
					type : 'ajax',
					url :contextPath+'/review/retriveAllCollegeTypes.do',
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
			productTypeStore.load();
			
			    
			Ext.define('productModel', {
				extend : 'Ext.data.Model',
				fields : [ 
				           {name:'collegeid', mapping:'collegeid',type:'int'},
				           {name:'collegeName', mapping:'collegeName',type:'string'}
						 ],
				idProperty: 'collegeid'
			});
			
			
			var genericStore = Ext.create('Ext.data.Store', {
				id : 'genericStoreId',
				name : 'genericStoreName',
				model : 'productModel',
				proxy : {
					type : 'ajax',
					url :contextPath+'/review/retriveAllCollegesForCollegeType.do',
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
			    
			 
			 
			 
			 
			

			contentPanel = Ext
					.create(
							'Ext.form.Panel',
							{
								title : 'Web Crawler',
								collapsible:true,
								width : 1000,
								height : 300,
								autoScroll : true,
								renderTo:'container',
								defaults : {
									padding : '15 100 15 100',
									labelAlign : 'top'
								},
								layout : {
									type : 'table',
									columns : 2
								},
								items : [
										{
											xtype : 'combo',
											fieldLabel : 'Select Type Web Site :',
											id : 'webUrlType',
											name : 'webUrlType',
											labelAlign : 'top',
											width : 150,
											queryMode : 'local',
											displayField : 'webUrlType',
											valueField : 'webUrlTypeId',
											triggerAction : 'all',
											store : webUrlTypeStore	
										},
										{
											xtype : 'textfield',
											fieldLabel : 'Enter the Web Site Url',
											id : 'webUrl',
											name : 'webUrl',
											labelAlign : 'top',
											width : 250,
											allowBlank:false,
											blankText:'Please enter the Web Site Url'
											},
										{
											xtype : 'combo',
											labelAlign : 'top',
											width : 150,
											fieldLabel : 'Product Type',
											id : 'collegeid',
											name : 'producttype',
											queryMode : 'local',
											displayField : 'collegeName',
											valueField : 'collegeTypeId',
											triggerAction : 'all',
											store : productTypeStore,
											listeners : {	
												'select' : function(combo,records) {
											
														var selValue=combo.value;
														genericStore.removeAll();
														sendProductTypeAndGetProductInfo(selValue);
													}
												}
										},
										{
											xtype : 'combo',
											labelAlign : 'top',
											width : 150,
											fieldLabel : 'Product Name',
											id : 'catid',
											name : 'catid',
											queryMode : 'local',
											editable : false,
											displayField : 'collegeName',
											valueField : 'collegeid',
											triggerAction : 'all',
											store :genericStore,
											listeners : {	
												'select' : function(combo,records) {
											
													
													}
												}
										},
										{
											xtype : 'button',
											text : 'Store Review',
											id : 'Save',
											disabled : false,
											handler : function(store, btn, args) {

												var reviewGenFormat = generateJSONRequestForAddingReview();
												urlLink = contextPath + '/review/storereviewForUrl.do';
												hideConfirmationMsg();
												doReviewGenerationRequest(reviewGenFormat,urlLink);
											}
										} ]
							});
							

			function sendProductTypeAndGetProductInfo(selValue)
			{
				var store=Ext.getCmp('catid').getStore();
				store.load(
					{
						url :contextPath+'/review/retriveAllProductsForProductType.do',
						params:{
									type:selValue
								}
					}
				);
			}
	
		});
		
		