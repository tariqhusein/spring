/*
 * File: app/view/OpenFileWindowViewController.js
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

Ext.define('MetaFileData.view.OpenFileWindowViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.openfilewindow',

    onButtonClick: function(button, e, eOpts) {
        var fileLoad=button.up('window').down('#fileUploadExcelFile');
        //Ext.Msg.alert('aaa',fileLoad.getValue());
        var myWindow=button.up('form').getForm();
        if(myWindow.isValid())

        {
            myWindow.submit(
            {
                url:'/file/upload',
                waitMsg: 'File uploading...',
                method:'POST',
                success: function(myWindow,response) {
                    // uploadWindow.close();
                    //if(success)
                    //{
                    //msg('Success','File loaded done ...!!');
                    var FileResId=Ext.util.JSON.decode(response.result.id);
                    console.log(FileResId);
                    if(FileResId !== null)
                    {
                        Ext.Ajax.request({
                            url: '/file/upload/metadata',
                            method  : 'GET',
                            params:{uploadedFileUniqueId:FileResId},
                            success: function(response){
                                //Ext.Msg.msg(FileRes);
                                console.log(response.responseText);
                                var fileresponse="";
                                try
                                {
                                    fileresponse=Ext.util.JSON.decode(response.responseText);
                                }
                                catch(ex)
                                {
                                    console.los (ex);
                                }
                                console.log(fileresponse.fileName);
                                //console.log(response.fileName);
                                var openFileName=button.up('window').down('#OpenFileFileName');
                                var openFileType=button.up('window').down('#OpenFileFileType');
                                var openFileSize=button.up('window').down('#OpenFileFileSize');
                                var openFileLastAccess=button.up('window').down('#OpenFileLastAccess');
                                var openFileLastModified=button.up('window').down('#OpenFileLastModified');
                                try
                                {
                                    //console.log(response.fileName);
                                    openFileName.value=fileresponse.fileName;
                                    //openFileName.refresh();
                                    openFileType.value=fileresponse.fileType;
                                    openFileSize.value=fileresponse.lastSearch;
                                    openFileLastModified.value=fileresponse.lastModified;
                                    openFileLastAccess.value=fileresponse.lastSearch;
                                }
                                catch(ex1)
                                {
                                    console.log (ex1);
                                }
                                Ext.Ajax.request({
                                    url: '/file/upload/columns',
                                    method  : 'GET',
                                    params:{uploadedFileUniqueId:FileResId},
                                    success: function(response1){
                                        var columnStr=Ext.getStore('ExcelColumnStore');
                                        columnStr.load();
                                    }
                                });
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


    }

});
