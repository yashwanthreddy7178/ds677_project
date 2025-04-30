package com.minit.aeap.modules.emerge.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.frames.commons.utils.IConstants;
import org.frames.commons.utils.StringUtil;
import org.frames.commons.utils.SysUtil;
import org.frames.sys.models.Users;

import sun.misc.BASE64Decoder;

import com.minit.aeap.custexception.DBException;
import com.minit.aeap.custexception.ParamException;
import com.minit.aeap.factory.ModulesServiceFactory;
import com.minit.aeap.modules.alert.models.Alert;
import com.minit.aeap.modules.base.actions.ModulesBaseAjaxAction;
import com.minit.aeap.modules.cs.models.Event;
import com.minit.aeap.modules.emerge.models.GenReport;
import com.minit.aeap.modules.emerge.models.SaveProject;
import com.minit.aeap.modules.emerge.models.Vwftype;
import com.minit.aeap.utils.DateUtil;


public class ProjectAndReportAjaxAction extends ModulesBaseAjaxAction {

	public void saveProject(ActionMapping map, ActionForm actionForm,
			HttpServletRequest req, HttpServletResponse res) {
		String alertid=req.getParameter("alertid");
		//String sysmpath=req.getParameter("sysmpath");
		String adata=req.getParameter("data");
		String bdata=adata.replace("\"", "'");
		//String data=bdata.replace(/[\n]/ig,'');
		//String data="[{'img':'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2Gqee/LhYumSvyl/fPWphxS7XnlpTtyoYku7uWfNssbn5NMPb+n5fT//1OPlY9968429Jitpqvu98PQTbe/z1pudV+yILbN7NaL5Pi+tlBje/6ywBQgAxBaEFmNqe1HRfPtQYsvrrxUrfnLgnFl+8v7FVad8cc5ty076l/L21vvGzpdfLFe/nHfIfygev/e2OZ9vVWw579D/uNdjH7p9TXmfrKK5+tRD+44t/YaiS777Dx1ji+Aym1cj6nU1mu+j+4z1aiUAEFtAaDFmr+kntjSrbsvbK53uW9l45W+KC77+l8Wd11005/YNl5xeLP3mXxebr1u612OyQiRno7z4zNby51tuWlnccc355Wy49Bfl+7rwiP/cuC2T+1QeuHVVeZ+LvvWBYverO8r4kslql0xW31S35TyXMpR854Pl7WKLwDKMCOIF/2i+n4otACC24B+GxowktLSNLe+cW7JXbOlw32z9qVabXPq9/9F4+4ofH9BY1VLddvkxn2jc987rLy52vvxCceXxny3+8M4qlVh+yhcWDB25TyTQnH/YX5S3PbRp7bwff6LOshP///K2+zdct+DnfLvY1JbYMlVb8xj+91VntwCA2IJ/FHqhYqxsaYktr770fF8rQ7La5cF3VqVkbr38V+VZKFVsmS+CVLFl64N3lz+/4dffK1euzPfx7975SnHThT8tD9vNwbzNYSarcVqnevwFX/tPbd++a8dL88aWKri0jr+b43k2ixfkYgsAiC0gtBgzkNjS65ktrbGlVeLJ9q2PFPeuXVauZsl9/nDb6jn3efTuW8ozWXLln9df29XTypa47+ari12vvFz+OnL7I3feXG5Lao4tzz7+h2LdeT8qP568jzmRpOhvK1HjikyuUjQzl3dmMN9bh7GVCADEFhBcjJms2PLiO7HlK+8qf5xDbnNmy/VnfrtcFVK9bdVZRxeP3Lm+eH3Xzr1ix7ZH729ciaiKLX+47ca2Mye2vPP42Hjlr8u3VZearj7OHc8/09g+dNcNl+0dSJru23xJ607biPqNLULLZF/amcn73urPGwDEFiboH4SCi5nm2JIVIt1OFRpeevapPx5ie/h7iqceuqfxXPn52vNOKVea5IpDTz90b7H2nJOKTSvO2Su23L36suLJ+++Ys42o+UDc5mkXW3LVolzNqN1VhvLr2/bOpaFzn+efemyv999V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'}]";
		JSONArray ja=JSONArray.fromObject(bdata);
		JSONObject je = ja.getJSONObject(0);
		//Event event=ModulesServiceFactory.getEventService().loadEvent(eventid);
		SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
		String date=format1.format(new Date());
		SimpleDateFormat format2=new SimpleDateFormat("HHmmss");
		SimpleDateFormat format3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dirPath = "/upload/modules/projectimg/" + date.replaceAll("-", "/");
		String filename=format2.format(new Date())+".jpg";
		String realpath =SysUtil.getProperty("realpath")+dirPath;
		
		try {
			saveimg(realpath,je.getString("imgsrc").toString(),filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//json类型的data转换成json对象
		
		
		SaveProject sp=new SaveProject();
		sp.setAlertid(alertid);
		sp.setPrjectname(je.getString("prjectname"));
		sp.setImg(dirPath+filename);
		Date date2=new  Date();
		sp.setDate(format3.format(date2));
		sp.setPlotting(je.getJSONArray("plotting").toString());
		sp.setAnalyzedimg(je.getJSONArray("analyzedimg").toString());
		sp.setQueryterm(je.getJSONArray("queryterm").toString());
		ModulesServiceFactory.getProjectAndReportService().saveProject(sp);
		JSONArray ja1=JSONArray.fromObject("[]");
		JSONObject jo = JSONObject.fromObject("{}");
		jo.put("projectid", sp.getId());
		ja1.add(jo.toString());
		outputSimpleJsonData(res, ja1.toString());
	}
	
	public void saveProjectForOneMap(ActionMapping map, ActionForm actionForm,
			HttpServletRequest req, HttpServletResponse res) {
		String adata=req.getParameter("data");
		String bdata=adata.replace("\"", "'");
		JSONArray ja=JSONArray.fromObject(bdata);
		JSONObject je = ja.getJSONObject(0);
		SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
		String date=format1.format(new Date());
		SimpleDateFormat format2=new SimpleDateFormat("HHmmss");
		SimpleDateFormat format3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dirPath = "/upload/modules/projectimg/" + date.replaceAll("-", "/");
		String filename=format2.format(new Date())+".jpg";
		String realpath =SysUtil.getProperty("realpath")+dirPath;
		
		try {
			saveimg(realpath,je.getString("imgsrc").toString(),filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//json类型的data转换成json对象
		
		String alertid=je.getJSONArray("WarmInf").getJSONObject(0).getString("alertid");
		SaveProject sp=new SaveProject();
		sp.setAlertid(alertid);
		sp.setPrjectname(je.getString("prjectname"));
		sp.setImg(dirPath+"/"+filename);
		Date date2=new  Date();
		sp.setDate(format3.format(date2));
		sp.setPlotting(je.getJSONArray("plotting").toString());
		sp.setAnalyzedimg(je.getJSONArray("analyzedimg").toString());
		sp.setQueryterm(je.getJSONArray("queryterm").toString());
		ModulesServiceFactory.getProjectAndReportService().saveProject(sp);
		JSONArray ja1=JSONArray.fromObject("[]");
		JSONObject jo = JSONObject.fromObject("{}");
		jo.put("projectid", sp.getId());
		jo.put("alertid", alertid);
		ja1.add(jo.toString());
		outputSimpleJsonData(res, ja1.toString());
	}
	
    

	public void genReport(ActionMapping map, ActionForm actionForm,
			HttpServletRequest req, HttpServletResponse res) {
		
		this.outputJsonData(res, IConstants.RESULT_TYPE_SUCCESS);
	}
	
	public static String getStr() throws IOException{
	FileReader fr=new FileReader("D:\\a.txt");
    BufferedReader br=new BufferedReader(fr);
    StringBuilder sb = new StringBuilder();
    String s ="";
    while((s =br.readLine())!=null){
    	sb.append(s);
    }
    String st=sb.toString();
    System.out.println(st);
    br.close();
    return st;
}

public static void saveimg(String realpath,String imgstr,String filename) throws IOException {
	
	  BASE64Decoder decoder=new BASE64Decoder();
	  byte[] bytes=decoder.decodeBuffer(imgstr);
	  File file=new File(realpath);
	  if(!file.exists()){
		  file.mkdirs();
	  }
	  FileOutputStream fos=new FileOutputStream(file.getPath()+"/"+filename);
	  fos.write(bytes);
	  fos.flush();
	  fos.close();
}


public void getImageList(ActionMapping map, ActionForm actionForm,
		HttpServletRequest req, HttpServletResponse res) {
	String projectid=req.getParameter("projectid");
	List<SaveProject> list=ModulesServiceFactory.getProjectAndReportService().getImglistByprojectid(projectid);
     
     //System.out.println(list.get(0).getImgsrc());
	 JSONObject jo = JSONObject.fromObject("{}");
     jo.put("imgsrc",SysUtil.getProperty("sys.voice.path")+list.get(0).getImg());
	 //jo.put("imgsrc","http://192.168.0.144:8086/TARS"+list.get(0).getImg());
     //System.out.println(jo.toString());
	 outputJsonData(res, 0, "", jo.toString());
}

/**
 * 根据eventid查询风险分析信息
 * @param map
 * @param actionForm
 * @param req
 * @param res
 */
public void promptlist(ActionMapping map, ActionForm actionForm,
                       HttpServletRequest req, HttpServletResponse res) {
      Users luser = this.getLoginUser(req);
      if (luser != null) {
            String eventid = StringUtil.getParamValue(req, "eventid","");
            if(!eventid.equals("")){
                List list = ModulesServiceFactory.getProjectAndReportService()
                            .getPromptList(eventid);
                outputSimpleJsonData(res, this.generatorJsonData(list));
            }
       }

}

//给gis返回方案
public void getSPGrid(ActionMapping map, ActionForm actionForm,
		HttpServletRequest req, HttpServletResponse res) {
	String alertid=req.getParameter("alertid");
	//String ownerorgid=req.getParameter("ownerorgid");
	//String status="";
	//Users luser = this.getLoginUser(req);
	List list=ModulesServiceFactory.getProjectAndReportService().getSpListByAlertId(alertid);
	String data=generatorSimpleJsonData(list);
	Alert alert = null;
	try {
		alert = ModulesServiceFactory.getAlertService().loadAlert(alertid);
	} catch (DBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParamException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	JSONArray ja=JSONArray.fromObject("[]");
	JSONObject jo = JSONObject.fromObject("{}");
	//jo.put("status", status);
	jo.put("alerttitle", alert.getAlertinfo().getHeadline());
	jo.put("alertdesc", alert.getAlertinfo().getDescription().replaceAll("<[^>]*>", ""));
	//jo.put("xy", event.getLongitude()+"_"+event.getLatitude());
	jo.put("prject", data);
	ja.add(jo.toString());
	System.out.println(jo.toString());
	this.outputSimpleJsonData(res, ja.toString());
}
 

public void getProjectAndReportById(ActionMapping map, ActionForm actionForm,
                      HttpServletRequest req, HttpServletResponse res) {
                  String projectid=req.getParameter("id");
                  SaveProject project=(SaveProject)ModulesServiceFactory.getProjectAndReportService().getProjectById(projectid);
                  GenReport report=(GenReport)ModulesServiceFactory.getProjectAndReportService().getReportByProjectid(projectid);
                  JSONArray ja=JSONArray.fromObject("[]");
                  ja.add(project.toJsonData());
                  if(report!=null){
                    ja.add(report.toJsonData());
                  }
                  
                  this.outputSimpleJsonData(res, ja.toString());
              }

public void genGisTreeGrid(ActionMapping map, ActionForm actionForm,
		HttpServletRequest req, HttpServletResponse res) {
	int page = StringUtil.getParamIntValue(req, "page", 1);
	int limit = StringUtil.getParamIntValue(req, "pagesize", 10);
	Users luser = this.getLoginUser(req);
	if (luser == null) {
		outputJsonData(res, IConstants.RESULT_TYPE_NOLOGIN);
	} else {
		String alertid = req.getParameter("alertid");
		Alert alert = null;
		try {
			alert = ModulesServiceFactory.getAlertService().loadAlert(alertid);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String eventtype=alert.getAlertinfo().getEventtype();
		List<Vwftype> list=ModulesServiceFactory.getProjectAndReportService().getGisTreeData(eventtype);
        
		String data = "";
		JSONArray all=JSONArray.fromObject("[]");
		JSONObject allo = JSONObject.fromObject("{}");
		//allo.put("x_y", event.getLatitude()+"_"+event.getLongitude());
		allo.put("label", "影响因素");
		allo.put("state", "0");
		
		
		/**
		 * 生成每个分类的json
		 */
		/*JSONArray danArry1=JSONArray.fromObject("[]");
		JSONArray defArray1=JSONArray.fromObject("[]");
		JSONArray jydArray1=JSONArray.fromObject("[]");
		JSONArray yjwArray1=JSONArray.fromObject("[]");
		JSONArray yjzArray1=JSONArray.fromObject("[]");*/
		String danjo1Data="";
		String defjo1Data="";
		String jydjo1Data="";
		String yjwjo1Data="";
		String yjzjo1Data="";
		
		for (Vwftype vwftype : list) {
			//List l=ModulesServiceFactory.getEventchainService().getVwftypeByPcode(vwftype.getCode());
			//if(l.size()==0){
			if(vwftype.getCode().startsWith("2")){
				JSONObject danjo1 = JSONObject.fromObject("{}");
				
				danjo1.put("label", vwftype.getName());
				danjo1.put("state", "0");
				danjo1.put("type", vwftype.getCode());
				danjo1.put("id", "DANGER");
				danjo1.put("childrens", "[]");
				danjo1Data+=danjo1.toString()+",";
				//danjo1Data+=danjo1Data+",";
				//danArry1.add(danjo1.toString()+",");
				
			}else if(vwftype.getCode().startsWith("3")){
				JSONObject defjo1 = JSONObject.fromObject("{}");
				defjo1.put("label", vwftype.getName());
				defjo1.put("state", "0");
				defjo1.put("type", vwftype.getCode());
				defjo1.put("id", "DEFENCE");
				defjo1.put("childrens", "[]");
				defjo1Data+=defjo1.toString()+",";
				//defjo1Data+=defjo1Data+",";
				//defArray1.add(defjo1.toString()+",");
				
			}else if((vwftype.getCode().substring(0,2)).equals("42")){
				JSONObject jydjo1 = JSONObject.fromObject("{}");
				jydjo1.put("label", vwftype.getName());
				jydjo1.put("state", "0");
				jydjo1.put("type", vwftype.getCode());
				jydjo1.put("id", "ETEAM");
				jydjo1.put("childrens", "[]");
				jydjo1Data+=jydjo1.toString()+",";
				//jydjo1Data+=jydjo1Data+",";
				//jydArray1.add(jydjo1.toString()+",");
				
			}else if((vwftype.getCode().substring(0,3)).equals("43D")){
				JSONObject yjzjo1 = JSONObject.fromObject("{}");
				yjzjo1.put("label", vwftype.getName());
				yjzjo1.put("state", "0");
				yjzjo1.put("type", vwftype.getCode());
				yjzjo1.put("id", "EQUIPMENT");
				yjzjo1.put("childrens", "[]");
				yjzjo1Data+=yjzjo1.toString()+",";
				//yjzjo1Data+=yjzjo1Data+",";
				//yjzArray1.add(yjzjo1.toString()+",");
				
			}else{
				JSONObject yjwjo1 = JSONObject.fromObject("{}");
				yjwjo1.put("label", vwftype.getName());
				yjwjo1.put("state", "0");
				yjwjo1.put("type", vwftype.getCode());
				yjwjo1.put("id", "MATERIAL");
				yjwjo1.put("childrens", "[]");
				yjwjo1Data+=yjwjo1.toString()+",";
				//yjwjo1Data+=yjwjo1Data+",";
				//yjwArray1.add(yjwjo1.toString()+",");
			}
		}	
	//	}		
        if(danjo1Data.endsWith(",")){
        	danjo1Data=danjo1Data.substring(0,danjo1Data.lastIndexOf(","));
        }
        if(defjo1Data.endsWith(",")){
        	defjo1Data=defjo1Data.substring(0,defjo1Data.lastIndexOf(","));
        }
        if(jydjo1Data.endsWith(",")){
        	jydjo1Data=jydjo1Data.substring(0,jydjo1Data.lastIndexOf(","));
        }
        if(yjwjo1Data.endsWith(",")){
        	yjwjo1Data=yjwjo1Data.substring(0,yjwjo1Data.lastIndexOf(","));
        }
        if(yjzjo1Data.endsWith(",")){
        	yjzjo1Data=yjzjo1Data.substring(0,yjzjo1Data.lastIndexOf(","));
        }
			
		/**
		 * 加入大分类
		 */
		for (Vwftype vwftype : list) {
			if(vwftype.getCode().startsWith("2")){
				//JSONArray danArry=JSONArray.fromObject("[]");
				JSONObject danjo = JSONObject.fromObject("{}");
				danjo.put("label", "危险源");
				danjo.put("state", "0");
				danjo.put("type", "20000");
				danjo.put("id", "DANGDR");
				danjo.put("childrens", "["+danjo1Data+"]");
				data+=danjo.toString()+",";
				//danArry.add(danjo.toString());
				break;
			}
		}
		for (Vwftype vwftype : list) {
			if(vwftype.getCode().startsWith("3")){
				//JSONArray defArray=JSONArray.fromObject("[]");
				JSONObject defjo = JSONObject.fromObject("{}");
				defjo.put("label", "防护目标");
				defjo.put("state", "0");
				defjo.put("type", "30000");
				defjo.put("id", "DEFENCE");
				defjo.put("childrens", "["+defjo1Data+"]");
				data+=defjo.toString()+",";
				//defArray.add(defjo.toString());
				break;	
			}
		}
		
		for (Vwftype vwftype : list) {
			if((vwftype.getCode().substring(0,2)).equals("42")){
				//JSONArray jydArray=JSONArray.fromObject("[]");
				JSONObject jydjo = JSONObject.fromObject("{}");
				jydjo.put("label", "救援队伍");
				jydjo.put("state", "0");
				jydjo.put("type", "42000");
				jydjo.put("id", "ETEAM");
				jydjo.put("childrens", "["+jydjo1Data+"]");
				data+=jydjo.toString()+",";
				//jydArray.add(jydjo.toString());
				break;
			}
		}
		
		for (Vwftype vwftype : list) {
			if(((vwftype.getCode().substring(0,2)).equals("43"))&&!((vwftype.getCode().substring(2,3)).equals("d"))){
				//JSONArray yjwArray=JSONArray.fromObject("[]");
				JSONObject yjwjo = JSONObject.fromObject("{}");
				yjwjo.put("label", "应急物资");
				yjwjo.put("state", "0");
				yjwjo.put("type", "43000");
				yjwjo.put("id", "MATERIAL");
				yjwjo.put("childrens", "["+yjwjo1Data+"]");
				data+=yjwjo.toString()+",";
				//yjwArray.add(yjwjo.toString());
				break;
			}
		}
		
		for (Vwftype vwftype : list) {
			if((vwftype.getCode().substring(0,3)).equals("43D")){
				//JSONArray yjzArray=JSONArray.fromObject("[]");
				JSONObject yjzjo = JSONObject.fromObject("{}");
				yjzjo.put("label", "应急装备");
				yjzjo.put("state", "0");
				yjzjo.put("type", "43D00");
				yjzjo.put("id", "EQUIPMENT");
				yjzjo.put("childrens", "["+yjzjo1Data+"]");
				data+=yjzjo.toString()+",";
				//yjzArray.add(yjzjo.toString());
				break;
			}
		}
		

		if(data.endsWith(",")){
			data=data.substring(0,data.lastIndexOf(","));
		}
		allo.put("childrens", "["+data+"]");
		all.add(allo.toString());
		this.outputSimpleJsonData(res, all.toString());
	}
}

public void getAlertInfoData(ActionMapping map, ActionForm actionForm,
		HttpServletRequest req, HttpServletResponse res) {
	
	String alertid=req.getParameter("alertid");
	Alert alert = null;
	try {
		alert = ModulesServiceFactory.getAlertService().loadAlert(alertid);
	} catch (DBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParamException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	JSONArray ja=JSONArray.fromObject("[]");
	JSONObject jo = JSONObject.fromObject("{}");
    jo.put("title", alert.getAlertinfo().getHeadline());
    jo.put("desc", alert.getAlertinfo().getDescription());
	ja.add(jo.toString());
    outputSimpleJsonData(res, ja.toString());
  }


	/**
	 * 检查报告文档是否存在
	 * 
	 * @param map
	 * @param actionForm
	 * @param req
	 * @param res
	 */
	public void getGenReport(ActionMapping map, ActionForm actionForm, HttpServletRequest req, HttpServletResponse res) {
		String projectid = req.getParameter("projectid");
		GenReport genReport = ModulesServiceFactory.getProjectAndReportService().getReportByProjectid(projectid);
		if (genReport == null) {
			outputSimpleJsonData(res, "no");
		} else {
			outputSimpleJsonData(res, genReport.toJsonData());
		}
	}
	
	/**
	 * 根据方案id和名称 获取报告
	 * 
	 * @param map
	 * @param actionForm
	 * @param req
	 * @param res
	 */
	public void getProjectByAlertIdAndName(ActionMapping map, ActionForm actionForm, HttpServletRequest req, HttpServletResponse res) {
		String alertid = req.getParameter("alertid");
		String projectname = req.getParameter("projectname");
		SaveProject project = ModulesServiceFactory.getProjectAndReportService().getProjectByAlertIdAndName(alertid, projectname);
		GenReport genReport = ModulesServiceFactory.getProjectAndReportService().getReportByProjectid(project.getId());
		if (genReport == null) {
			outputSimpleJsonData(res, null);
		} else {
			outputSimpleJsonData(res, genReport.toJsonData());
		}
	}


}


