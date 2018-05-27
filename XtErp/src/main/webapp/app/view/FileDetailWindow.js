/*
 * File: app/view/FileDetailWindow.js
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

Ext.define('MetaFileData.view.FileDetailWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.filedetailwindow',

    requires: [
        'MetaFileData.view.FileDetailWindowViewModel',
        'Ext.tab.Panel',
        'Ext.tab.Tab',
        'Ext.form.field.Text'
    ],

    viewModel: {
        type: 'filedetailwindow'
    },
    cls: 'TextClass',
    height: 434,
    width: 533,
    title: 'File Details',

    items: [
        {
            xtype: 'tabpanel',
            activeTab: 0,
            items: [
                {
                    xtype: 'panel',
                    title: 'File Information'
                },
                {
                    xtype: 'panel',
                    title: 'Data'
                }
            ]
        },
        {
            xtype: 'textfield',
            fieldLabel: 'FIle Name'
        },
        {
            xtype: 'textfield',
            fieldLabel: 'File Type'
        },
        {
            xtype: 'textfield',
            fieldLabel: 'Size'
        },
        {
            xtype: 'textfield',
            fieldLabel: 'Last Modefied'
        },
        {
            xtype: 'textfield',
            fieldLabel: 'Last Access'
        },
        {
            xtype: 'textfield',
            fieldLabel: 'Owners'
        },
        {
            xtype: 'textfield',
            fieldLabel: 'Contents'
        }
    ]

});