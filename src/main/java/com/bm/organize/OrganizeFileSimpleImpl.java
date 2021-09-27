package com.bm.organize;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class OrganizeFileSimpleImpl implements OrganizeFile {

  @Value("${folder.name.format}")
  private String folderNameFormat;

  @Override
  public void organize(String folderPath) {

    log.info("Folder Name Format: {}, e.g {}", folderNameFormat,
        DateFormatUtils.format(new Date(), folderNameFormat));

    organizeFiles(folderPath);

  }

  private void organizeFiles(String folderPath) {
    File folder = Paths.get(folderPath).toFile();

    if (null == folder || !folder.exists()) {
      throw new IllegalArgumentException("Invalid folder path: " + folderPath);
    }

    File[] files = folder.listFiles();
    Map<String, List<File>> group = new HashMap<>();

    for (File file : files) {
      Date date = new Date(file.lastModified());
      SimpleDateFormat dateFormat = new SimpleDateFormat(folderNameFormat);
      final String key = dateFormat.format(date);
      List<File> fileList = group.get(key);
      if (null == fileList) {
        fileList = new ArrayList<>();
        group.put(key, fileList);
      }
      fileList.add(file);
    }

    for (Map.Entry<String, List<File>> entry : group.entrySet()) {
      File dateFolder = Paths.get(folder.getAbsolutePath(), entry.getKey()).toFile();
      for (File file : entry.getValue()) {
        if(file.isDirectory() && !file.getName().matches("[0-9]+-[a-zA-Z]+")) {
          organizeFiles(file.getAbsolutePath());
        } else {
          try {
            if(!file.isDirectory()) {
              FileUtils.moveFileToDirectory(file, dateFolder, true);
            }
          } catch (IOException e) {
            log.warn("Unable to organize file: {} Reason {}", file.getAbsolutePath(), e.getMessage());
          }
        }
      }
    }
  }


}
