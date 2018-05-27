/*
 * File: app/view/ExtErp/Main/MainPanelViewModel.js
 *
 * This file was generated by Sencha Architect version 4.2.3.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 6.5.x Classic library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 6.5.x Classic. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('MetaFileData.view.ExtErp.Main.MainPanelViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.exterp.main.mainpanel',

    requires: [
        'Ext.data.Store',
        'Ext.data.field.Field',
        'Ext.data.proxy.Ajax',
        'Ext.data.reader.Json'
    ],

    stores: {
        metaFileStoreMainForm: {
            model: 'MetaFileData.model.MetaFileModel',
            fields: [
                {
                    name: 'fileName'
                },
                {
                    name: 'filePath'
                },
                {
                    name: 'fileType'
                },
                {
                    name: 'fileContent'
                },
                {
                    name: 'lastSearch'
                },
                {
                    name: 'lastModified'
                },
                {
                    name: 'fileSize'
                }
            ],
            proxy: {
                type: 'ajax',
                url: '/XtErp/file/upload/metadata'
            }
        },
        columnsStoreMainForm: {
            model: 'MetaFileData.model.ExcelColumnModel',
            fields: [
                {
                    name: 'name'
                },
                {
                    name: 'dataType'
                },
                {
                    name: 'excelColumnIndex'
                }
            ],
            proxy: {
                type: 'ajax',
                url: '/XtErp/file/upload/columns',
                reader: {
                    type: 'json'
                }
            }
        },
        EntityStore: {
            autoLoad: true,
            proxy: {
                type: 'ajax',
                url: '/XtErp/entities',
                reader: {
                    type: 'json',
                    transform: function(data) {
                        data=data.map(function(val){return {entityName:val};
                    });
                    return data;

                    }
                }
            },
            fields: [
                {
                    name: 'entityName'
                }
            ]
        },
        importableColumnStore: {
            model: 'MetaFileData.model.importableColumn',
            fields: [
                {
                    name: 'id'
                },
                {
                    name: 'name'
                },
                {
                    name: 'dataType'
                }
            ],
            proxy: {
                type: 'ajax',
                url: '/XtErp/entities/Client/columns'
            }
        }
    }

});