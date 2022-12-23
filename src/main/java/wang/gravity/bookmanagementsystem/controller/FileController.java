package wang.gravity.bookmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import wang.gravity.bookmanagementsystem.annotation.PassToken;
import wang.gravity.bookmanagementsystem.annotation.UserLogAnnotation;
import wang.gravity.bookmanagementsystem.constant.UserLogConst;
import wang.gravity.bookmanagementsystem.service.FileService;
import wang.gravity.bookmanagementsystem.utils.ResultUtil;
import wang.gravity.bookmanagementsystem.utils.UploadFileUtil;

@RestController
public class FileController {

    @Autowired
    FileService fileService;
    @PassToken
    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public ResultUtil<String> upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileUrl = fileService.uploadFile(multipartFile);
        return ResultUtil.success(fileUrl);
    }
}
