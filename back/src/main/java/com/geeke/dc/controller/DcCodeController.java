package com.geeke.dc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.geeke.common.data.Parameter;
import com.geeke.dc.entity.DcClienScheme;
import com.geeke.dc.entity.DcMobileScheme;
import com.geeke.dc.entity.DcScheme;
import com.geeke.dc.service.DcClienSchemeService;
import com.geeke.dc.service.DcMobileSchemeService;
import com.geeke.dc.service.DcSchemeService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.FileUtils;
import com.geeke.utils.StringUtils;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;


/**
 * 生成代码Controller
 * @author lxl
 * @version 2022-09-07
 */
@RestController
@RequestMapping(value = "/dc/download")
public class DcCodeController extends BaseController {

	@Autowired
	private DcSchemeService dcSchemeService;

	@Autowired
    private DcClienSchemeService dcClienSchemeService;

	@Autowired
	private DcMobileSchemeService dcMobileSchemeService;


	/**
	 * 打包下载项目的移动端、前端和后端代码
	 * @param projectId  项目id
	 * @param response   http请求响应
	 * @throws IOException
	 */
	@GetMapping("project/{projectId}")
	public void downloadProject(@PathVariable("projectId") String projectId, HttpServletResponse response) throws IOException {
		String tmpDir = getTempDir();
		String fileName = null;

		Parameter param = new Parameter("project_id", "=", projectId);
		List<Parameter> params = Lists.newArrayList();
		params.add(param);

		// 生成后端代码
		List<DcScheme> backSchemes = dcSchemeService.listAll(params, "");
		for (DcScheme scheme : backSchemes) {
			DcScheme backScheme = dcSchemeService.get(scheme.getId());
			genFiles(tmpDir, backScheme.getTemplateJson());
			if(fileName == null) {
				fileName = handleFileName(backScheme.getProject().getName());
			}
		}

		// 生成前端代码
		List<DcClienScheme> clientSchemes = dcClienSchemeService.listAll(params, "");
		for (DcClienScheme scheme : clientSchemes) {
			DcClienScheme clentScheme = dcClienSchemeService.get(scheme.getId());
			genFiles(tmpDir, clentScheme.getTemplateJson());
			if(fileName == null) {
				fileName = handleFileName(clentScheme.getProject().getName());
			}
		}

		// 生成移动端代码
		List<DcMobileScheme> mobileSchemes = dcMobileSchemeService.listAll(params, "");
		for (DcMobileScheme scheme : mobileSchemes) {
			DcMobileScheme mobileScheme = dcMobileSchemeService.get(scheme.getId());
			genFiles(tmpDir, mobileScheme.getTemplateJson());
			if(fileName == null) {
				fileName = handleFileName(mobileScheme.getProject().getName());
			}
		}

		zip2Response(response, tmpDir, fileName);
		FileUtils.deleteDirectory(tmpDir);
	}

	/**
	 * 打包下载项目的后端代码
	 * @param projectId  项目id
	 * @param response   http请求响应
	 * @throws IOException
	 */
	@GetMapping("back/{projectId}")
	public void downloadBack(@PathVariable("projectId") String projectId, HttpServletResponse response) throws IOException {
		String tmpDir = getTempDir();
		String fileName = null;

		Parameter param = new Parameter("project_id", "=", projectId);
		List<Parameter> params = Lists.newArrayList();
		params.add(param);
		List<DcScheme> schemes = dcSchemeService.listAll(params, "");
		for (DcScheme scheme : schemes) {
			DcScheme backScheme = dcSchemeService.get(scheme.getId());
			genFiles(tmpDir, backScheme.getTemplateJson());
			if(fileName == null) {
				fileName = handleFileName(backScheme.getProject().getName());
			}
		}

		zip2Response(response, tmpDir, fileName);
		FileUtils.deleteDirectory(tmpDir);
	}

	/**
	 * 打包下载项目的前端代码
	 * @param projectId  项目id
	 * @param response   http请求响应
	 * @throws IOException
	 */
	@GetMapping("client/{projectId}")
	public void downloadClient(@PathVariable("projectId") String projectId, HttpServletResponse response) throws IOException {
		String tmpDir = getTempDir();
		String fileName = null;

		Parameter param = new Parameter("project_id", "=", projectId);
		List<Parameter> params = Lists.newArrayList();
		params.add(param);
		List<DcClienScheme> schemes = dcClienSchemeService.listAll(params, "");
		for (DcClienScheme scheme : schemes) {
			DcClienScheme clentScheme = dcClienSchemeService.get(scheme.getId());
			genFiles(tmpDir, clentScheme.getTemplateJson());
			if(fileName == null) {
				fileName = handleFileName(clentScheme.getProject().getName());
			}
		}

		zip2Response(response, tmpDir, fileName);
		FileUtils.deleteDirectory(tmpDir);
	}
	
	/**
	 * 下载后端端方案的代码
	 * @param shemeId        后端方案方案id
	 * @param response	http请求响应
	 * @throws IOException
	 */
	@GetMapping("backScheme/{shemeId}")
	public void downloadBackScheme(@PathVariable("shemeId") String shemeId, HttpServletResponse response) throws IOException {
		DcScheme dcScheme = dcSchemeService.get(shemeId);
		String tmpDir = getTempDir();
		genFiles(tmpDir, dcScheme.getTemplateJson());

		// 处理中文文件名的问题
		String fileName = handleFileName(dcScheme.getProject().getName());

		zip2Response(response, tmpDir, fileName);
		
		FileUtils.deleteDirectory(tmpDir);
	}

	/**
	 * 下载前端方案的代码
	 * @param shemeId        前端方案方案id
	 * @param response  http请求响应
	 * @throws IOException
	 */
	@GetMapping("clientScheme/{shemeId}")
	public void downloadClientScheme(@PathVariable("shemeId") String shemeId, HttpServletResponse response) throws IOException {
		DcClienScheme clientScheme = dcClienSchemeService.get(shemeId);
		String tmpDir = getTempDir();
		genFiles(tmpDir, clientScheme.getTemplateJson());

		// 处理中文文件名的问题
		String fileName = handleFileName(clientScheme.getProject().getName());

		zip2Response(response, tmpDir, fileName);
		
		FileUtils.deleteDirectory(tmpDir);
	}

	/**
	 * 下载移动端方案的代码
	 * @param mobileId        移动端方案方案id
	 * @param response  http请求响应
	 * @throws IOException
	 */
	@GetMapping("mobileScheme/{mobileId}")
	public void downloadMobileScheme(@PathVariable("mobileId") String mobileId, HttpServletResponse response) throws IOException {
		DcMobileScheme dcMobileScheme = dcMobileSchemeService.get(mobileId);
		String tmpDir = getTempDir();
		genFiles(tmpDir, dcMobileScheme.getTemplateJson());

		// 处理中文文件名的问题
		String fileName = handleFileName(dcMobileScheme.getProject().getName());

		zip2Response(response, tmpDir, fileName);

		FileUtils.deleteDirectory(tmpDir);
	}

	/**
	 * 打包下载项目的移动端代码
	 * @param projectId  项目id
	 * @param response   http请求响应
	 * @throws IOException
	 */
	@GetMapping("mobile/{projectId}")
	public void downloadMobile(@PathVariable("projectId") String projectId, HttpServletResponse response) throws IOException {
		String tmpDir = getTempDir();
		String fileName = null;

		Parameter param = new Parameter("project_id", "=", projectId);
		List<Parameter> params = Lists.newArrayList();
		params.add(param);
		List<DcMobileScheme> mobileSchemes = dcMobileSchemeService.listAll(params, "");
		for (DcMobileScheme scheme : mobileSchemes) {
			DcMobileScheme mobileScheme = dcMobileSchemeService.get(scheme.getId());
			genFiles(tmpDir, mobileScheme.getTemplateJson());
			if(fileName == null) {
				fileName = handleFileName(mobileScheme.getProject().getName());
			}
		}

		zip2Response(response, tmpDir, fileName);
		FileUtils.deleteDirectory(tmpDir);
	}

	/**
	 * 打包压缩文件夹内的文件到response
	 * @param response   http请求响应
	 * @param dir		 要压缩的文件夹
	 * @param fileName	 压缩后的文件名
	 * @throws IOException
	 */
	private void zip2Response(HttpServletResponse response, String dir,  String fileName) throws IOException {
		response.setContentType("application/x-zip-compressed");
		response.setHeader("Content-Disposition", "attchment;filename=" + fileName);
		ZipOutputStream zouts = new ZipOutputStream(response.getOutputStream());
		FileUtils.zipDirectoryToZipFile(dir, new File(dir), zouts);
		zouts.close();
	}

	/**
	 * 处理文件名中文
	 * @param fileName  文件名
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String handleFileName(String fileName) throws UnsupportedEncodingException {
		String newFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		newFileName = new String(newFileName.getBytes("UTF-8"), "GBK");
		return newFileName;
	}

	/**
	 * 获取临时文件处理目录
	 * @return
	 */
	private String getTempDir() {
		String tmpdir = System.getProperty("java.io.tmpdir");
		return tmpdir + UUID.randomUUID();
	}

	/**
	 * 代码模板中的代码生成文件
	 * @param tmpDir
	 * @param strJson
	 * @return
	 */
	private String genFiles(String tmpDir, String strJson) {
		StringBuilder result = new StringBuilder();
		Map<String, String> mapType = JSON.parseObject(strJson, Map.class);

		List<Map> list = JSONArray.parseArray(JSON.toJSONString(mapType.get("pages"))).toJavaList(Map.class);
		for (int i = 0; i < list.size(); i++) {
			String fileName = tmpDir + File.separator + StringUtils.replaceEach(String.valueOf(list.get(i).get("filePath")) + "/", new String[]{"//", "/", "."}, new String[]{File.separator, File.separator, File.separator}) + list.get(i).get("fileName");
			logger.debug(" fileName === " + fileName);
			String content = String.valueOf(list.get(i).get("code"));
			logger.debug(" content === \r\n" + content);
			if (FileUtils.createFile(fileName)) {
				FileUtils.writeToFile(fileName, content, true);
				logger.debug(" file create === " + fileName);
				result.append( "生成成功：" + fileName + "<br/>");
			} else {
				logger.debug(" file extents === " + fileName);
				result.append("<font color='#E6A23C'>文件已存在：" + fileName + "</font><br/>");
			}
		}
		return result.toString();
	}

	

}
