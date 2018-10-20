
package com.spring.upload;

import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;


@Component
public interface ServiceUpload
{
    public void upload(String imgPath,String fileName,UploadedFile uploadFile);

}
