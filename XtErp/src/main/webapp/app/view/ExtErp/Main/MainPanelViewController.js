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
        var myent=button.up('form').down('#entityName');
        var alldata=button.up('form').down('#allDataLbl');
        var myclmnt=button.up('form').down('#columnName');
        myLbl.setVisible(false);
        myent.setVisible(false);
        myclmnt.setVisible(false);
        alldata.setVisible(false);
        //myLbl.setText("Before get");
        if(myWindow.isValid())

        {
            myWindow.submit(
            {
                url:'/XtErp/file/upload',
                waitMsg: 'File uploading...',
                method:'POST',
                success: function(myWindow,response) {
                    var FileResId=Ext.util.JSON.decode(response.result.id);
                    console.log(FileResId);
                    myLbl.setText(FileResId);
                    if(FileResId !== null)
                    {
                        Ext.Ajax.request({
                            url: '/XtErp/file/upload/metadata',
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
                url: '/XtErp/file/upload/columns',
                method  : 'GET',
                params:{uploadedFileUniqueId:FileResId},
                //jsonData:excelColumn,
                success: function(response2){
                    console.log(response2.responseText);
                    var responseData=Ext.util.JSON.decode(response2.responseText);
                    //var columnsStr=Ext.getStore('')
                    console.log(responseData);
                    var columnGrid= button.up('form').down('#ColumnsGridPanelMainForm');
                    columnGrid.getStore().loadData(responseData);
                }
            });
            Ext.Ajax.request({
                url: '/XtErp/entities',
                method  : 'GET',
                success: function(response3){
                    console.log(response3.responseText);
                    var responseData2=Ext.util.JSON.decode(response3.responseText);
                    console.log(responseData2);
                }
            });
        }
        catch(ex)
        {
            console.log(ex);
        }
    },

    onComboboxSelect: function(combo, record, eOpts) {
        try
        {


            var entityName1=combo.getValue();
            var myent=combo.up('form').down('#entityName');

            myent.setText(entityName1);
            var myUrl='/XtErp/entities/'+entityName1+'/columns';
            console.log(myUrl);

            Ext.Ajax.request({

                url: myUrl,
                method  : 'GET',

                //jsonData:excelColumn,
                success: function(response2){
                    console.log(response2.responseText);
                    var responseData=Ext.util.JSON.decode(response2.responseText);
                    //var columnsStr=Ext.getStore('')
                    console.log(responseData);
                    var columnCmb= combo.up('form').down('#entityColumnName');
                    var Istore= columnCmb.getStore();
                    Istore.getProxy().setUrl(myUrl);
                    Istore.loadData(responseData);
                }
            });


        }
        catch(ex1)
        {
            console.log(ex1);
        }
    },

    onEntityColumnNameSelect: function(combo, record, eOpts) {
        try
        {var myent=combo.up('form').down('#entityName').text;

            var myclmn=combo.up('form').down('#columnName');
            myclmn.setText(combo.getValue());
            var clmn=combo.getValue();
            var alldata=view.up('form').down('#allDataLbl').text.split(',');
            var found=false;
            for(var i=0;i<alldata.length;i++)
            {
                if(alldata[i] ==myent+'-'+clmn)
                {
                    found=true;
                    Ext.Msg.alert('already exits column');
                    combo.focus(true);
                    break;
                }
            }
        }
        catch(ext)
        {

        }
    },

    onButtonClick: function(button, e, eOpts) {


        var myLbl=button.up('form').down('#metaFileNamelbl');
        var fileResId=myLbl.text;
        var pbar3=button.up('form').down('#importingProgressBar');
        pbar3.wait({
            interval: 1000,
            duration: 50000,
            increment: 50,
            text:'importing data,please waite!!...',
            fn:function() {
                //button.dom.disabled = false;
                pbar3.updateText('importing done !!!');
                button.setText('Import Data');
            }
        });
        Ext.Ajax.request({

            url: '/XtErp/file/upload/import',
            method  : 'POST',
            params:{uploadedFileUniqueId:fileResId},
            success:function(response3)
            {
                console.log(response3.responseText);
                Ext.Msg.alert('success','import done !!!!');
            }
        });
    },

    onProgressbarUpdate: function(progressbar, value, text, eOpts) {
        //var btn=progressbar.up('form').down('#importDataBtn');
        //btn.setText('importing ....');
        //btn.dom.disable=true;
    },

    onButtonClick21: function(button, e, eOpts) {

    }

});
