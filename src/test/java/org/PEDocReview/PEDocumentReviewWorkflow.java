package org.PEDocReview;

import com.dropbox.core.DbxDownloader;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.testng.annotations.Test;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import com.google.api.services.sheets.v4.model.*;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

/***
 make sure every documents manually reviewed by somebody
 make sure don't forget a document
 documents are reviewed appropriately
 download the documents and upload the documents in google drive
 automate pulling the files from dropbox and upload to google drive
 automated integration using slack API
 one file may contain 2-100 pages


 download the file (20 files) -
 spreadsheet all 20 files listed, having who are assigned, status, reviewed, progress, push notifications
 that spreadsheet will be shared to



 1.

 */


public class PEDocumentReviewWorkflow {
//    private static final String ACCESS_TOKEN = "TNUQve-oF7gAAAAAAAAAAXX9CFMyOVDO5Pa258WP3dQPkrq1PLEtKWIUGRYBp2FK";
//    DbxRequestConfig config = new DbxRequestConfig("dropbox/pe-doc-review");
//    DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
//    private Sheets service;

//    @Test
//    public void TC_01_ReadDropdboxFiles() throws DbxException, IOException {
////        try {
////            // Create Dropbox client
////
////
////            // Get current account info
////            FullAccount account = client.users().getCurrentAccount();
////            System.out.println(account.getName().getDisplayName());
////
////            // Get files and folder metadata from Dropbox root directory
////            ListFolderResult result = client.files().listFolder("");
////            while (true) {
////                for (Metadata metadata : result.getEntries()) {
////                    System.out.println(metadata.getPathLower());
////                }
////
////                if (!result.getHasMore()) {
////                    break;
////                }
////
////                result = client.files().listFolderContinue(result.getCursor());
////            }
////
////            // Upload "test.txt" to Dropbox
//////            try (InputStream in = new FileInputStream("/sample.txt")) {
//////                FileMetadata metadata = client.files().uploadBuilder("/sample.txt")
//////                        .uploadAndFinish(in);
////
////                //Download the file from Dropbox
////            {
////                FileOutputStream out = new FileOutputStream("/hello.gdoc");
////                DbxDownloader<FileMetadata> downloader = null;
////                FileMetadata response;
////
////                response = downloader.getResult();
////                try {
////                    InputStream in = downloader.getInputStream();
////                } finally {
////                    downloader.close();
////                }
////            }
//
//            } catch (DbxException exl) {
//                exl.printStackTrace();
//            }
//    }


////    @Test
//    public void TC_02_DownloadDropdboxFiles() throws DbxException, IOException {
//        try {
//
//            String localPath = "G:\\Request.gsheet";
//            OutputStream outputStream = new FileOutputStream(localPath);
//            FileMetadata metadata = client.files()
//                    .downloadBuilder("Request.gsheet")
//                    .download(outputStream);
//
//        } catch (DbxException exl) {
//            exl.printStackTrace();
//        }
//    }
    }

