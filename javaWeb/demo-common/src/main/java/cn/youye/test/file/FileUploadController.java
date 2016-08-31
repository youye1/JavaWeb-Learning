package cn.youye.test.file;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2016/7/15.
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @RequestMapping(value = "/oneUpload")
    public String oneUpload(@RequestParam("fileTest") MultipartFile fileTest,
                            HttpServletRequest request, ModelMap model) {

        String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";
        String fileName = fileTest.getOriginalFilename();

        File dir = new File(uploadUrl);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        System.out.println("上传文件到：" + uploadUrl + fileName);
        File targetFile = new File(uploadUrl + fileName);
        if (!targetFile.exists()) {
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            fileTest.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:http://localhost:8080/demo-test/upload/" + fileName;
    }

    @RequestMapping("/twoUpload")
    public String twoUpload(HttpServletRequest request, Model model) {
        MultipartHttpServletRequest httpReq = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = httpReq.getFileMap();

        String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";

        File dir = new File(uploadUrl);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        List<String> fileList = new ArrayList<>();
        for (MultipartFile file : fileMap.values()) {
            String fileName = file.getOriginalFilename();
            File targetFile = new File(uploadUrl + fileName);
            if (!targetFile.exists()) {
                try {
                    targetFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    file.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fileList.add("http://localhost:8080/demo-test/upload/" + fileName);
        }
        model.addAttribute("files", fileList);
        return "modules/file/fileShow";
    }

}
