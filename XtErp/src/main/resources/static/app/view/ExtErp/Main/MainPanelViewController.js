/*
 * File: app/view/ExtErp/Main/MainPanelViewController.js
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

Ext.define('MetaFileData.view.ExtErp.Main.MainPanelViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.exterp.main.mainpanel',

    onUploadButtonMainFormClick: function(button, e, eOpts) {
        var fileLoad=button.up('form').down('#uploadFileMainForm');
        var myWindow=button.up('form').getForm();
        var myLbl=button.up('form').down('#metaFileNamelbl');
        myLbl.setVisible(false);
        //myLbl.setText("Before get");
        if(myWindow.isValid())

        {
            myWindow.submit(
            {
                url:'/file/upload',
                waitMsg: 'File uploading...',
                method:'POST',
                success: function(myWindow,response) {
                    var FileResId=Ext.util.JSON.decode(response.result.id);
                    console.log(FileResId);
                    myLbl.setText(FileResId);
                    if(FileResId !== null)
                    {
                        Ext.Ajax.request({
                            url: '/file/upload/metadata',
                            method  : 'GET',

                            params:{uploadedFileUniqueId:FileResId},
                            success: function(response1){
                                console.log(response1.responseText);
                                try
                                {

                                }
                                catch(ex)
                                {

                                }
                                // myLbl.setText("After get");
                                //myLbl.setText("File name is : "+Ext.util.JSON.decode(response1.responseText).fileName);

                                /*
                                console.log(response.responseText);
                                var fileresponse="";
                                try
                                {

                                }
                                catch(ex)
                                {
                                console.los (ex);
                                }
                                console.log(fileresponse.fileName);
                                //console.log(response.fileName);
                                */


                                try
                                {
                                    fileresponse=Ext.util.JSON.decode(response1.responseText);
                                    var openFileName=button.up('form').down('#fileNameField');
                                    // var openFileType=button.up('form').down('#OpenFileFileType');
                                    var openFileSize=button.up('form').down('#fileSizeField');
                                    var openFileLastAccess=button.up('form').down('#fileLastSearchField');
                                    var openFileLastModified=button.up('form').down('#fileLastModifiedField');
                                    //console.log(response.fileName);
                                    openFileName.setValue(fileresponse.fileName);
                                    //openFileName.refresh();
                                    //openFileType.value=fileresponse.fileType;
                                    openFileSize.setValue(fileresponse.fileSize);
                                    openFileLastModified.setValue(fileresponse.lastModified);
                                    openFileLastAccess.setValue(fileresponse.lastSearch);



                                }
                                catch(ex1)
                                {
                                    console.log (ex1);
                                }
                                // button.up('window').loader.load( button.up('window').autoload.url);
                                // doLayout();
                            }
                        });
                    }
                },
                failure: function(form, action) {
                    Ext.Msg.msg('File uploading', 'Error during upload report template.');
                }
            });
        }


    },

    onButtonClick1: function(button, e, eOpts) {
        try
        {
            var FileResId=button.up('form').down('#metaFileNamelbl').text;
            console.log(FileResId);
            Ext.Ajax.request({
                url: '/file/upload/columns',
                method  : 'GET',
                params:{uploadedFileUniqueId:FileResId},
                //jsonData:excelColumn,
                success: function(response2){
                    console.log(response2);
                    // console.log(Ext.util.JSON.decode(response2.result));
                    var columnGrid= button.up('form').down('#ColumnsGridPanelMainForm');
                    columnGrid.getStore().load();
                }
            });
        }
        catch(ex)
        {
            console.log(ex);
        }
    }

});