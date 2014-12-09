package com.wt.studio.plugin.pagedesigner.wizard.templates;

import java.util.List;
import java.io.Serializable;

import com.wt.studio.plugin.pagedesigner.gef.model.FunctionColumnModel;
import com.wt.studio.plugin.pagedesigner.gef.model.MOFunctionTableModel;
import com.wt.studio.plugin.pagedesigner.gef.model.TableConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.TableotnConnection;
import com.wt.studio.plugin.pagedesigner.gef.model.TableotoConnection;
import com.wt.studio.plugin.pagedesigner.utils.TemplateUtils;

public class BOServiceTemplate
{
  protected static String nl;
  public static synchronized BOServiceTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    BOServiceTemplate result = new BOServiceTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package com.hirisun.pqsm.service;" + NL + "import java.io.IOException;" + NL + "import java.util.List;" + NL + "" + NL + "import javax.servlet.http.HttpServletRequest;" + NL + "" + NL + "import com.hirisun.pqsm.bo.BInfSupRwPqsRiskEvaBo;" + NL + "import com.hirisun.pqsm.model.BInfSupRwPqsRiskEva;" + NL + "import com.hirisun.pqsm.model.SearchConditions;" + NL + "public interface BInfSupRwPqsRiskEvaBoService {" + NL + "" + NL + "\tpublic BInfSupRwPqsRiskEvaBo getBOById(String riskEvaId, int level);" + NL + "" + NL + "\t/**" + NL + "\t * 转换BO对象为实体对象" + NL + "\t * " + NL + "\t * @param binfSupRwPqsRiskEvaBo binfSupRwPqsRiskEvaBo" + NL + "\t * @return BInfSupRwPqsRiskEva" + NL + "\t */" + NL + "\tpublic BInfSupRwPqsRiskEva toEntity(BInfSupRwPqsRiskEvaBo binfSupRwPqsRiskEvaBo);" + NL + "" + NL + "\t/**" + NL + "\t * 保存BO对象到数据库" + NL + "\t * " + NL + "\t * @param binfSupRwPqsRiskEvaBo " + NL + "\t * @return BInfSupRwPqsRiskEvaBo" + NL + "\t */" + NL + "\tpublic BInfSupRwPqsRiskEvaBo save(BInfSupRwPqsRiskEvaBo binfSupRwPqsRiskEvaBo);" + NL + "" + NL + "\t/**" + NL + "\t * 将实体对象转换为BO对象" + NL + "\t * " + NL + "\t * @param binfSupRwPqsRiskEva " + NL + "\t * @return BInfSupRwPqsRiskEvaBo" + NL + "\t */" + NL + "\tpublic BInfSupRwPqsRiskEvaBo toBO(BInfSupRwPqsRiskEva binfSupRwPqsRiskEva);" + NL + "" + NL + "\t/**" + NL + "\t * 生成指定级别的BO对象" + NL + "\t * " + NL + "\t * @param binfSupRwPqsRiskEva " + NL + "\t * @param level level" + NL + "\t * @return BInfSupRwPqsRiskEvaBo" + NL + "\t */" + NL + "\tpublic BInfSupRwPqsRiskEvaBo toBO(BInfSupRwPqsRiskEva binfSupRwPqsRiskEva, int level);" + NL + "" + NL + "\t/**" + NL + "\t * 通过实体对象的list对象生成BO的list对象" + NL + "\t * " + NL + "\t * @param listBInfSupRwPqsRiskEva " + NL + "\t * @param level level" + NL + "\t * @return List<BInfSupRwPqsRiskEvaBo>" + NL + "\t */" + NL + "\tpublic List<BInfSupRwPqsRiskEvaBo> toBOs(List<BInfSupRwPqsRiskEva> listBInfSupRwPqsRiskEva, int level);" + NL + "" + NL + "\t/**" + NL + "\t * 按照某属性获得所有BO对象列表" + NL + "\t * " + NL + "\t * @param propertyName propertyName" + NL + "\t * @param propertyValue propertyValue" + NL + "\t * @param level level" + NL + "\t * @return List" + NL + "\t */" + NL + "\tpublic List<BInfSupRwPqsRiskEvaBo> getBOs(String propertyName, Object propertyValue," + NL + "\t\t\tint level);" + NL + "" + NL + "\t/**" + NL + "\t * 根据Json格式的查询条件BO对象列表" + NL + "\t * " + NL + "\t * @param searchConditions json格式的查询条件" + NL + "\t * @param level level" + NL + "\t * @return List" + NL + "\t */" + NL + "\tpublic List<BInfSupRwPqsRiskEvaBo> getBOs(SearchConditions searchConditions, int level);" + NL + "" + NL + "\t/**" + NL + "\t * 方法说明:" + NL + "\t * " + NL + "\t * @param hSql hSql" + NL + "\t * @param paras paras" + NL + "\t * @param level level" + NL + "\t * @return list" + NL + "\t */" + NL + "\tpublic List<BInfSupRwPqsRiskEvaBo> getBOs(String hSql, String[] paras, int level);" + NL + "" + NL + "\t/**" + NL + "\t * 删除指定风险评估处置" + NL + "\t * " + NL + "\t * @param riskEvaId  " + NL + "\t * @return Boolean" + NL + "\t */" + NL + "\tpublic Boolean delBInfSupRwPqsRiskEva(String riskEvaId);" + NL + "\t" + NL + "\t/**" + NL + "\t * " + NL + "\t * 方法说明：删除风险评估信息、涉及范围、评估指标、研判方式" + NL + "\t *" + NL + "\t * @param riskEvaId " + NL + "\t * @return String" + NL + "\t */" + NL + "\tpublic String delRiskEva(String riskEvaId);" + NL + "\t" + NL + "\t/**" + NL + "\t * " + NL + "\t * 方法说明：保存风险评估信息、涉及范围、评估指标、研判方式" + NL + "\t *" + NL + "\t * @param postdata " + NL + "\t * @param request " + NL + "\t * @return String" + NL + "\t * @throws IOException" + NL + "\t */" + NL + "\tpublic String saveRiskEva(String postdata, HttpServletRequest request) throws IOException;" + NL + "\t" + NL + "}";
  protected final String TEXT_2 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
