package wang.gravity.bookmanagementsystem.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import wang.gravity.bookmanagementsystem.service.FileService;
import wang.gravity.bookmanagementsystem.utils.UploadFileUtil;


@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(MultipartFile file) {
        return UploadFileUtil.uploadFile(file);
    }
}
