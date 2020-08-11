
package com.leftorright.rsscreator.ueditor.upload;

import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.ueditor.PathFormat;
import com.leftorright.rsscreator.ueditor.define.AppInfo;
import com.leftorright.rsscreator.ueditor.define.BaseState;
import com.leftorright.rsscreator.ueditor.define.FileType;
import com.leftorright.rsscreator.ueditor.define.State;
import com.leftorright.rsscreator.utils.FileUtils;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BinaryUploader {
	static Logger logger = LoggerFactory.getLogger(BinaryUploader.class);

    static final String reg = "(10|172|192|127)\\.([0-1][0-9]{0,2}|[2][0-4][0-9]{0,1}|[2][5][0-5]{0,1}|[2][6-9]{0,1}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-4][0-9]{0,1}|[2][5][0-5]{0,1}|[2][6-9]{0,1}|[3-9][0-9]{0,1})\\.([0-1][0-9]{0,2}|[2][0-4][0-9]{0,1}|[2][5][0-5]{0,1}|[2][6-9]{0,1}|[3-9][0-9]{0,1})";

    private static final String FORM_FILE_FIELD_NAME = "file";

    public static final State save(HttpServletRequest request, Map<String, Object> conf) {
		State state = null;
		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;
		boolean isInner = getIsInner(request);
		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

		if ( isAjaxUpload ) {
			upload.setHeaderEncoding( "UTF-8" );
		}

		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multipartRequest.getFile("upfile");
			//获得保存路径
			String savePath = (String) conf.get("savePath");
			String localSavePathPrefix = (String) conf.get("localSavePathPrefix");
			//文件原始名称
			String originFileName = file.getOriginalFilename();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}
			savePath = PathFormat.parse(savePath, originFileName);
			localSavePathPrefix = localSavePathPrefix + savePath;
			//文件保存的真实物理路径
			String physicalPath = localSavePathPrefix;
			logger.info("BinaryUploader physicalPath:{},savePath:{}",localSavePathPrefix,savePath);
			InputStream is = file.getInputStream();

			//在此处调用ftp的上传图片的方法将图片上传到文件服务器
			String path = physicalPath.substring(0, physicalPath.lastIndexOf("/")+1) ;
			String picName = physicalPath.substring(physicalPath.lastIndexOf("/")+1, physicalPath.length());
			picName=picName.substring(0, picName.length() - suffix.length());
			logger.info("path:{},picName:{}",path,picName);
			//这里就是把文件保存到硬盘上，具体怎么保存的可以自己跟过去看看
			//State这个类很重要，是一个接口，它是返回到前端的数据
//			State storageState = StorageManager.saveFileByInputStream(request, is, path, picName, maxSize);
			String flag = uploadCommonFile(multipartRequest,path,picName,"upfile");
			is.close();
			if (flag != null) {
				state = new BaseState(true);
				state.putInfo( "size", file.getSize() );
				state.putInfo( "title", picName+suffix);//文件名填入此处
				state.putInfo("group", "");//所属group填入此处
				// 传递一个code值

//				Result result = SpringUtil.getBean(ResourceClient.class).getStaticServerUrl(ResourceTypeEnum.TYPE_PICTURE.getName(), code, isInner);
				Result result = new Result();
                String staticUrl = FileUtils.getFileUrl();
                String finalPath = path+picName+suffix;
				state.putInfo( "url", staticUrl
						+ finalPath.substring(finalPath.indexOf("pic") + 4,(path+picName+suffix).length()));//文件访问的url填入此处
				state.putInfo("type", suffix);
				state.putInfo("original", originFileName + suffix);
			}else{
				state = new BaseState(false, 4);
			}
			/*if (storageState.isSuccess()) {
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}*/

			return state;
		} catch (Exception e) {
			logger.error("文件上传程序异常", e);
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		}
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}


    /**
     * 获取项目前缀url
     * @return
     */
    public static boolean getIsInner(HttpServletRequest req) {
        String serverUrl = req.getRequestURL().toString();  //形如：http://127.0.0.1:8000/platform/api/login
        String serverUri = req.getRequestURI();             //形如：/platform/api/login
        String commonIp = serverUrl.substring(0, serverUrl.lastIndexOf(serverUri));    //形如：http://127.0.0.1:8000
        String url = commonIp + req.getContextPath();       //形如：http://127.0.0.1:8000/platform
            //运行时配置文件没有配置内网
        boolean inner = isInner(url);

        return inner;
    }

    public static boolean isInner(String ip) {
        logger.info("ip>>>>" + ip);
        //获取运行时配置ip
            Pattern p = Pattern.compile(reg);
            Matcher matcher = p.matcher(ip);
            return matcher.find();
        
    }

    /**
     * 通用上传功能
     *
     * @param req
     * @param storePath          存储路径，为空则取默认值
     * @param storeName          存储名称，为空则取默认值
     * @param fieldFormFieldName form表单文件组件名称，为空择取默认值 FORM_FILE_FIELD_NAME
     * @return
     */
    public static String uploadCommonFile(MultipartHttpServletRequest req, String storePath,
                                          String storeName, String fieldFormFieldName) {
        try {
            if (StringUtils.isEmpty(fieldFormFieldName)) {
                fieldFormFieldName = FORM_FILE_FIELD_NAME;
            }
            if (StringUtils.isEmpty(storeName)) {
                storeName = UUID.randomUUID().toString().replaceAll("-", "");
            }
            return uploadCore(req, storePath, storeName, fieldFormFieldName);
        } catch (Exception e) {
            logger.error("uploadCommonFile exception :", e);
            return null;
        }
    }

    /**
     * 上传核心功能， 可以像 uploadCommonFile 方法，对其进行封装
     *
     * @param req
     * @param storePath          存储路径， 为空取默认值
     * @param storeName          存储名称， 不可以为空
     * @param fieldFormFieldName form表单文件组件名称，不可以为空
     * @return
     * @throws Exception
     */
    private static String uploadCore(MultipartHttpServletRequest req, String storePath, String storeName,
                                     String fieldFormFieldName) throws Exception {
        // 根据前台的name名称得到上传的文件:uploadFile
        MultipartFile uploadFile = req.getFile(fieldFormFieldName);
        String originFileName = uploadFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.indexOf("."));        // 扩展名
        if (StringUtils.isEmpty(storePath)) {
            storePath = "/app/file/";
        }
        storeName += ext;
        File file = creatFolder(storePath, storeName);
        uploadFile.transferTo(file);
        return storePath + storeName;
    }

    /**
     * 创建文件
     *
     * @param path     绝对路径
     * @param fileName 文件名
     * @return
     */
    public static File creatFolder(String path, String fileName) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (notEmpty(fileName))
            file = new File(file, fileName);
        return file;
    }

    /**
     * 不为empty(空)
     *
     * @param str
     * @return
     */
    public static boolean notEmpty(Object str) {
        boolean result = false;
        if (null != str && !str.toString().isEmpty()) {
            result = true;
        }
        return result;
    }

}
