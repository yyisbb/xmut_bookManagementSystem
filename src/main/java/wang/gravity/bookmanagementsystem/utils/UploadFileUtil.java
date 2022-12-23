package wang.gravity.bookmanagementsystem.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.InetAddress;
import java.util.UUID;

import wang.gravity.bookmanagementsystem.constant.ErrorCode;
import wang.gravity.bookmanagementsystem.exception.MyException;

@Component
public class UploadFileUtil {

    private static final String PORT = PropertiesUtil.getProjectPort();
    private static final String URL_ROOT = "http://";

    /**
     * 项目路径
     */
    private static final String contextPath = "";

    public static String uploadFile(MultipartFile multipartFile) {
        try {
            if (multipartFile.isEmpty()) {
                throw new MyException(ErrorCode.UPLOAD_ERROR);
            }
            // 获取文件的名称
            String originalFilename = multipartFile.getOriginalFilename();
            // 文件后缀 例如：.png
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // uuid 生成文件名
            String uuid = String.valueOf(UUID.randomUUID());
            // 根路径，在 resources/static/upload
            String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/upload/";
            // 新的文件名，使用uuid生成文件名
            String fileName = uuid + fileSuffix;
            // 创建新的文件
            File fileExist = new File(basePath);
            // 文件夹不存在，则新建
            if (!fileExist.exists()) {
                fileExist.mkdirs();
            }
            // 获取文件对象
            File file = new File(basePath, fileName);
            // 完成文件的上传
            multipartFile.transferTo(file);
            // 返回绝对路径
            return URL_ROOT + InetAddress.getLocalHost().getHostAddress() + ":" + PORT + contextPath + "/upload/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new MyException(ErrorCode.UPLOAD_ERROR);
    }
}

