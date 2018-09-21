Ext.require( [ 'Ext.grid.*', 'Ext.data.*', 'Ext.form.*',
		'Ext.layout.container.Column', 'Ext.tab.Panel' ]);

Ext.Loader.setConfig( {
	enabled : true
});
var loadMask;
var hideConfirmationMsg;
var showConfirmationMsg;
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
	var companyId=Ext.getCmp('companyId').getValue();
	if(companyId!=null)
	{
		reviewGen.companyId=companyId;
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
							var value=errorObj[i].msg;
							showConfirmationMsg(value);
					}
					loadMask.hide();
				}
				else
				{
					var value=JsonData.message;
					showConfirmationMsg(value);
					loadMask.hide();
					contentPanel.hide();
					
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
			
			Ext.define('productTypeModel', {
				extend : 'Ext.data.Model',
				fields : [ 
				           {name:'companyTypeId', mapping:'companyTypeId',type:'int'},
				           {name:'companyName', mapping:'companyName',type:'string'}
						 ],
				idProperty: 'companyTypeId'
			});
			
			
			var productTypeStore = Ext.create('Ext.data.Store', {
				id : 'productTypeStoreId',
				name : 'productTypeStoreName',
				model : 'productTypeModel',
				proxy : {
					type : 'ajax',
					url :contextPath+'/review/retriveAllCompanyTypes.do',
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
					url :contextPath+'/review/retriveAllCompaniesForYahooFinance.do',
					extraParams:{
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
								title : 'Yahoo Finance Input',
								width : 800,
								height : 300,
								autoScroll : true,
								renderTo:'container',
								collapsible:true,
								defaults : {
										padding:'0 0 0 25',
									labelAlign : 'top'
								},
								layout : {
									type : 'table',
									columns : 1
								},
								items : [
										{
											xtype : 'combo',
											labelAlign : 'top',
											width : 150,
											fieldLabel : 'Company Name',
											id : 'companyId',
											name : 'companyId',
											queryMode : 'local',
											editable : false,
											displayField : 'companyName',
											valueField : 'companyId',
											triggerAction : 'all',
											store :genericStore,
											listeners : {	
												'select' : function(combo,records) {
											
													
													}
												}
										},
										{
											xtype : 'button',
											text : 'Yahoo Finance',
											id : 'Save',
											disabled : false,
											padding:'0 25 25 -17',
											handler : function(store, btn, args) {

												var reviewGenFormat = generateJSONRequestForAddingReview();
												urlLink = contextPath + '/review/performYahoo.do';
												hideConfirmationMsg();
												doReviewGenerationRequest(reviewGenFormat,urlLink);
											}
										}]
							});
							
			
			function sendProductTypeAndGetProductInfo(selValue)
			{
				var store=Ext.getCmp('catid').getStore();
				store.load(
					{
						url :contextPath+'/review/retriveAllCompaniesForCompanyType.do',
						params:{
									type:selValue
								}
					}
				);
					
			}
				
	
		});
		
		