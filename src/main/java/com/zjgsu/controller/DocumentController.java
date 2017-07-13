package com.zjgsu.controller;

import com.zjgsu.entity.Document;
import com.zjgsu.entity.Notice;
import com.zjgsu.entity.User;
import com.zjgsu.service.HrmService;
import com.zjgsu.util.common.HrmConstants;
import com.zjgsu.util.tag.PageModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 文档Controller
 * Created by zby on 2017/7/11.
 */
@Controller
public class DocumentController {
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * @param pageIndex
     * @param model
     * @param document
     * @return
     */
    // TODO 查询时点击下一页 参数不会传递过来
    @RequestMapping("/document/selectDocument")
    private String selectDocuemnt(Integer pageIndex, Model model, Document document){
        System.out.println("document = " + document);
        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        model.addAttribute("document",document);
        List<Document> documents = hrmService.findDocument(document,pageModel);
        model.addAttribute("documents",documents);
        model.addAttribute("pageModel",pageModel);
        return "document/document";
    }

    @RequestMapping("/document/removeDocument")
    private String removeDocument(String ids){
        String[] idArray = ids.split(",");
        for (String s : idArray){
            hrmService.removeDocumentById(Integer.parseInt(s));
        }
        return "redirect:/document/selectDocument";
    }

    @RequestMapping("/document/addDocument")
    private String addDocument(String flag, Document document, HttpSession session) throws IOException {
        if (flag.equals("1")){
            return "document/showAddDocument";
        } else {
            // 上传文件
            // String path = session.getServletContext().getRealPath("/upload/");
            String path = HrmConstants.UPLOAD_PATH;
            String fileName = document.getFile().getOriginalFilename();
            document.getFile().transferTo(new File(path + File.separator + fileName));

            // 插入数据库
            document.setFilename(fileName);
            User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
            document.setUser(user);
            hrmService.addDocument(document);
            return "redirect:/document/selectDocument";
        }
    }

    @RequestMapping("/document/updateDocument")
    private String updateDocument(String flag, Document document, Model model, HttpSession session) throws IOException {
        if (flag.equals("1")){
            Document target = hrmService.findDocumentById(document.getId());
            model.addAttribute("document",target);
            return "document/showUpdateDocument";
        } else {
            // 更新的时候如果有文件替换,需要将
            // 1.服务器地址的file文件替换
            // 2.数据库中的filename更改过来
            // 上传文件
            if (!document.getFile().isEmpty()){
                // String path = session.getServletContext().getRealPath("/upload/");
                String path = HrmConstants.UPLOAD_PATH;
                String fileName = document.getFile().getOriginalFilename();
                // 更新前先删除
//                FileUtils.forceDelete(new File(path + File.separator + fileName));
                document.getFile().transferTo(new File(path + File.separator + fileName));

                // 插入数据库
                document.setFilename(fileName);
                User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
                document.setUser(user);
            }
            hrmService.modifyDocument(document);
            return "redirect:/document/selectDocument";
        }
    }

    @RequestMapping("/document/downLoad")
    public ResponseEntity<byte[]> downLoad(Integer id, HttpSession session) throws IOException {
        Document target = hrmService.findDocumentById(id);
        String fileName = target.getFilename();

        // 获取文件上传路径
        // String path = session.getServletContext().getRealPath("/upload/");
        String path = HrmConstants.UPLOAD_PATH;
        // 获得要下载文件的File对象
        File file = new File(path + File.separator + fileName);
        // 创建springframework的HttpHeaders对象
        HttpHeaders headers = new HttpHeaders();
        // 下载显示的文件名,解决中文名称乱码问题
        String downloadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        // 通知浏览器以attachment(下载方式)打开图片
        headers.setContentDispositionFormData("attachment",downloadFileName);
        // application/octet-stream:二进制流数据(最常见的文件下载)
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 201 HttpStatus.CREATED
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }
}
