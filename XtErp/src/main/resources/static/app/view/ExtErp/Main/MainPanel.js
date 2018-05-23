/*
 * File: app/view/ExtErp/Main/MainPanel.js
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

Ext.define('MetaFileData.view.ExtErp.Main.MainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.exterp.main.mainpanel',

    requires: [
        'MetaFileData.view.ExtErp.Main.MainPanelViewModel',
        'MetaFileData.view.ExtErp.Main.MainPanelViewController',
        'Ext.tab.Panel',
        'Ext.tab.Tab',
        'Ext.form.Panel',
        'Ext.form.field.File',
        'Ext.form.Label',
        'Ext.form.field.Display',
        'Ext.grid.Panel',
        'Ext.grid.column.Number',
        'Ext.view.Table',
        'Ext.grid.column.Widget',
        'Ext.form.field.ComboBox',
        'Ext.grid.column.Action'
    ],

    controller: 'exterp.main.mainpanel',
    viewModel: {
        type: 'exterp.main.mainpanel'
    },
    height: 423,
    width: 800,
    title: '',

    items: [
        {
            xtype: 'tabpanel',
            height: 645,
            activeTab: 0,
            items: [
                {
                    xtype: 'panel',
                    title: 'Excel Files',
                    items: [
                        {
                            xtype: 'form',
                            height: 510,
                            bodyPadding: 10,
                            title: '',
                            method: 'POST',
                            url: '/file/upload',
                            items: [
                                {
                                    xtype: 'filefield',
                                    anchor: '100%',
                                    itemId: 'uploadFileMainForm',
                                    fieldLabel: 'Choose the file you want to upload',
                                    name: 'uploadedFile'
                                },
                                {
                                    xtype: 'button',
                                    itemId: 'uploadButtonMainForm',
                                    text: 'Upload',
                                    listeners: {
                                        click: 'onUploadButtonMainFormClick'
                                    }
                                },
                                {
                                    xtype: 'tabpanel',
                                    activeTab: 0,
                                    items: [
                                        {
                                            xtype: 'panel',
                                            title: 'File meta Data',
                                            items: [
                                                {
                                                    xtype: 'label',
                                                    itemId: 'columnName',
                                                    text: 'My Label'
                                                },
                                                {
                                                    xtype: 'label',
                                                    itemId: 'entityName',
                                                    text: 'My Label'
                                                },
                                                {
                                                    xtype: 'label',
                                                    itemId: 'metaFileNamelbl',
                                                    text: 'File Name'
                                                },
                                                {
                                                    xtype: 'displayfield',
                                                    itemId: 'fileNameField',
                                                    fieldLabel: 'File Name',
                                                    value: 'Display Field'
                                                },
                                                {
                                                    xtype: 'displayfield',
                                                    itemId: 'fileSizeField',
                                                    fieldLabel: 'File Size',
                                                    value: 'Display Field'
                                                },
                                                {
                                                    xtype: 'displayfield',
                                                    itemId: 'fileLastSearchField',
                                                    fieldLabel: 'last search',
                                                    value: 'Display Field',
                                                    inputType: 'datetime'
                                                },
                                                {
                                                    xtype: 'displayfield',
                                                    itemId: 'fileLastModifiedField',
                                                    fieldLabel: 'last modified',
                                                    value: 'Display Field'
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'panel',
                                            title: 'Columns',
                                            items: [
                                                {
                                                    xtype: 'button',
                                                    text: 'Get Columns',
                                                    listeners: {
                                                        click: 'onButtonClick1'
                                                    }
                                                },
                                                {
                                                    xtype: 'gridpanel',
                                                    itemId: 'ColumnsGridPanelMainForm',
                                                    title: '',
                                                    bind: {
                                                        store: '{columnsStoreMainForm}'
                                                    },
                                                    columns: [
                                                        {
                                                            xtype: 'numbercolumn',
                                                            dataIndex: 'excelColumnIndex',
                                                            text: 'Column Index'
                                                        },
                                                        {
                                                            xtype: 'gridcolumn',
                                                            width: 160,
                                                            dataIndex: 'name',
                                                            text: 'Column Name'
                                                        },
                                                        {
                                                            xtype: 'widgetcolumn',
                                                            text: 'Entities',
                                                            widget: {
                                                                xtype: 'combobox',
                                                                name: 'entityName',
                                                                displayField: 'entityName',
                                                                bind: {
                                                                    store: '{EntityStore}'
                                                                },
                                                                listeners: {
                                                                    select: 'onComboboxSelect'
                                                                }
                                                            }
                                                        },
                                                        {
                                                            xtype: 'widgetcolumn',
                                                            text: 'Columns',
                                                            widget: {
                                                                xtype: 'combobox',
                                                                itemId: 'entityColumnName',
                                                                name: 'entityColumnName',
                                                                displayField: 'name',
                                                                valueField: 'name',
                                                                bind: {
                                                                    store: '{importableColumnStore}'
                                                                },
                                                                listeners: {
                                                                    select: 'onEntityColumnNameSelect'
                                                                }
                                                            }
                                                        },
                                                        {
                                                            xtype: 'actioncolumn',
                                                            width: 50,
                                                            text: 'Attach',
                                                            iconCls: 'x-fa fa-paperclip',
                                                            items: [
                                                                {
                                                                    handler: function(view, rowIndex, colIndex, item, e, record, row) {
                                                                        var FileResId=view.up('form').down('#metaFileNamelbl').text;
                                                                        console.log("Put request");
                                                                        console.log("fileId"+FileResId);
                                                                        var myent=view.up('form').down('#entityName').text;
                                                                        console.log("entity: "+myent );
                                                                        var myclmn=view.up('form').down('#columnName').text;
                                                                        console.log("column: "+myclmn);
                                                                        Ext.defer(function () {
                                                                            Ext.Msg.confirm('Attach Column','Are you sure you want to set the attachment?',
                                                                            function (button) {
                                                                                if (button == 'yes') {
                                                                                    var store =view.up('form').down('#ColumnsGridPanelMainForm').getStore();
                                                                                    var rec = store.getAt(rowIndex);
                                                                                    console.log(record);
                                                                                    console.log("row index "+ rec.get('excelColumnIndex'));
                                                                                    Ext.Ajax.request({
                                                                                        url: '/entities',
                                                                                        params:{fileId:FileResId,columnIndex:rec.get('excelColumnIndex'),entity:myent,entityColumn:myclmn},
                                                                                        method  : 'PUT',
                                                                                        success: function(response){
                                                                                            Ext.Msg.alert('success','attach done !!!!');
                                                                                        }
                                                                                    });
                                                                                }
                                                                            });
                                                                        });
                                                                    },
                                                                    altText: 'Link!!',
                                                                    iconCls: 'x-fa fa-paperclip'
                                                                }
                                                            ]
                                                        }
                                                    ]
                                                }
                                            ]
                                        },
                                        {
                                            xtype: 'panel',
                                            title: 'Contents',
                                            items: [
                                                {
                                                    xtype: 'button',
                                                    text: 'Import data',
                                                    listeners: {
                                                        click: 'onButtonClick'
                                                    }
                                                }
                                            ]
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    title: 'Profiles'
                },
                {
                    xtype: 'panel',
                    title: 'Tab 3'
                }
            ]
        }
    ]

});