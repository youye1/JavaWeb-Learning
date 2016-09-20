package cn.youye.uploadFile;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by pc on 2016/9/18.
 */
public class UploadFileServlet extends HttpServlet {
    private static final long serialVersionUID = -4935921396709035718L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action=req.getParameter("action");
        if ("one".equals(action)){
            uploadOne(req,resp);
        }else if ("more".equals(action)){
            uploadMore(req,resp);
        }else {
            req.getRequestDispatcher("index.jsp").forward(req,resp);
            return;
        }

    }

    private void uploadOne(HttpServletRequest req,HttpServletResponse resp){
        /**
         * 单文件上传
         */
    }

    private void uploadMore(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        String uploadUrl = req.getSession().getServletContext().getRealPath("/") + "upload/";
        DiskFileItemFactory factory=new DiskFileItemFactory();

        factory.setRepository(new File(uploadUrl));
        factory.setSizeThreshold(1024*1024);

        ServletFileUpload upload=new ServletFileUpload(factory);
        String url="http://localhost:8080/demo-servlet/upload/";

        try {
            List<FileItem> list=upload.parseRequest(req);
            for (FileItem item:list){
                String name=item.getFieldName();
                if (item.isFormField()){
                    String value=item.getString();
                    req.setAttribute(name,value);
                }else {
                    String value=item.getName();
                    if (value.equals("")){
                        continue;
                    }
//                    int start=value.lastIndexOf(".");
//                    String filename=value.substring(start+1);
                    String filename= UUID.randomUUID().toString().replace("-","");
                    req.setAttribute(name,url+filename);

                    //写文件
                    OutputStream out=new FileOutputStream(new File(uploadUrl,filename));

                    InputStream in=item.getInputStream();

                    int length=0;
                    byte[] buf=new byte[1024];
                    System.out.println("获取上传文件的总容量： "+item.getSize());

                    while ((length=in.read(buf))!=-1){
                        out.write(buf,0,length);
                    }
                    in.close();
                    out.close();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        for (int i=0;i<names.size();i++){
//            req.setAttribute("file"+(i+1),url+names.get(i));
//        }
        req.getRequestDispatcher("/WEB-INF/views/modules/uploadFile/fileShow.jsp").forward(req,resp);
        return;
    }

}
