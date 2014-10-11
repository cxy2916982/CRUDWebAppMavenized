package com.cx.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cx.dao.CommonDao;
import com.cx.util.GenericClass;

@SuppressWarnings("unchecked")
public class CommonDaoImpl<T> implements CommonDao<T> {
	
	/**获取父类的泛型类型***/
	@SuppressWarnings("rawtypes")
	private Class entityClass=GenericClass.getGenericClass(this.getClass());
	
	@Autowired
	private SessionFactory session ;

	public void save(T entity) {
		session.getCurrentSession().save(entity);
	}
	

	public void update(T entity) {
		session.getCurrentSession().update(entity);
	}

	public T findObjectById(Serializable id) {
		if(id==null){
			throw new RuntimeException("您要查找的["+id+"]不能为空");
		}
		return (T)session.getCurrentSession().get(entityClass, id);
	}

	/**
	 * 批量删除,通过多id删除多条记录
	 */
	public void deleteByIds(Serializable... ids) {
		if(ids!=null&&ids.length>0){
			for(Serializable id:ids){
				Object entity=session.getCurrentSession().get(entityClass, id);
				if(entity==null){
					throw new RuntimeException("您要查找的["+id+"]不存在"); 
				}
				this.session.getCurrentSession().delete(entity);
			}
		}
	}
	public void deleteAllObjects(Collection<T> entities) {
		for(Object entity : entities)
		session.getCurrentSession().delete(entity);
	}

	public List<T> findObjectsByConditionWithNoPage(String whereHql, final Object[] params, 
			   LinkedHashMap<String, String> orderby) {
		
		//产生select语句 SELECT * FROM SysUserGroup o WHERE 
		String hql="select o from "+entityClass.getSimpleName() +"  o  where 1=1  ";
		//System.out.println("hql  "+hql);
		
		//在select语句的后面这查询条件
		if(StringUtils.isNotEmpty(whereHql)){
			hql=hql+whereHql;
		}
		//处理排序
		String orderbystr=buildOrderBy(orderby);
		//System.out.println("orderbystr  "+orderbystr);
		hql=hql+orderbystr;
		//System.out.println("hql  "+hql);
		
		final String fhql=hql;
		
		List<T>  list=session.getCurrentSession().createQuery(fhql).list() ;
		return list;
	}

	
	public List<T> findObjectsByConditionWithNoPage(String whereHql,Object[] params) {
		return this.findObjectsByConditionWithNoPage(whereHql, params,null);
	}
	

	public List<T> findObjectsByConditionWithNoPage() {
		return this.findObjectsByConditionWithNoPage(null, null,null);
	}
	
	public List<T> findObjectsByConditionWithNoPage(LinkedHashMap<String, String> orderby) {
		return this.findObjectsByConditionWithNoPage(null, null,orderby);
	}
	
	/**
	 * 设置hql语句需要的参数
	 * @param query
	 * @param params
	 */
	public void setParams(Query query, Object[] params) {
		if(params!=null&&params.length>0){
			for(int i=0;i<params.length;i++){
				query.setParameter(i, params[i]);
			}
		}
	}
	
	/*
	 *  组织排序条件
	 *  orderby.put("o.id", "asc");
		orderby.put("o.name", "desc");
	 */
	public String buildOrderBy(LinkedHashMap<String, String> orderby) {
		StringBuffer buf=new StringBuffer("");
		if(orderby!=null&&!orderby.isEmpty()){
			buf.append("  order by ");
			for(Map.Entry<String, String> em:orderby.entrySet()){
				buf.append(em.getKey()+"  "+em.getValue()+",");
			}
			//去掉最后一个","
			buf.deleteCharAt(buf.length()-1);
		}
		return buf.toString();
	}

}
