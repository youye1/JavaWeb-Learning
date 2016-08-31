package cn.youye.test.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by pc on 2016/8/3.
 */
@Controller
@RequestMapping("/img")
public class ImgJcropController {

    @RequestMapping("/topage")
    public String toPage(String pageName) {
        return "modules/" + pageName;
    }

    @ResponseBody
    @RequestMapping(value = "/uploadImg")
    public Map<String, String> uploadImg(@RequestParam(value = "imageWeb", required = false) MultipartFile image,
                                         HttpServletRequest request) {

        String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";
        String fileName = image.getOriginalFilename();
        String checkFlag = "yes";
        boolean checkTrue = true;
        Map<String, String> map = new HashMap<>();
        JSON json = new JSONObject();
        try {
            if (!Pattern.compile("jpg$|gif$|jpge$|png$", Pattern.CASE_INSENSITIVE).matcher(fileName).find()) { //文件格式
                checkFlag = "errorExt";
                checkTrue = false;
            } else if (Pattern.compile(";|%", Pattern.CASE_INSENSITIVE).matcher(fileName).find()) {
                checkFlag = "errorPoint";
                checkTrue = false;
            } else if (image.getSize() / 1048576 > 30) {//3M
                checkFlag = "errorSize";
                checkTrue = false;
            }
            if (checkTrue) {
                File dir = new File(uploadUrl);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                System.out.println("上传文件到：" + uploadUrl + fileName);
                File targetFile = new File(uploadUrl + fileName);
                if (!targetFile.exists()) {
                    targetFile.createNewFile();
                }
                image.transferTo(targetFile);
                map.put("imageUrl", "http://localhost:8080/ssm-jcrop/upload/" + fileName);
                map.put("relativeUrl", uploadUrl + fileName);
            }
            map.put("checkFlag", checkFlag);
        } catch (Exception e) {
            throw new HTTPException(404);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/imgCut", method = RequestMethod.POST)
    public Map<String, Object> imgCut(String target, String hrefImgCrop, int x, int y, int w, int h) throws IOException {

        Map<String, Object> map = new HashMap<>();
        FileInputStream is = null;
        ImageInputStream iis = null;
        //获取图片的后缀名 即格式
        String ext = hrefImgCrop.substring(hrefImgCrop.lastIndexOf(".") + 1);
        try {

            is = new FileInputStream(hrefImgCrop);
            //缺点在于只能上传一种格式的图片
            //急需改进
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(ext);
            ImageReader reader = it.next();
            iis = ImageIO.createImageInputStream(is);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();

            Rectangle rect = new Rectangle(x, y, w, h);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);

            ImageIO.write(bi, ext, new File(hrefImgCrop));
            map.put("target", target);
        } catch (Exception e) {
            map.put("errorCode", e);
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
            if (iis != null) {
                iis.close();
            }
        }
        return map;
    }
}
