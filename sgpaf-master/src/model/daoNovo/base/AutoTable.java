package model.daoNovo.base;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.google.common.base.CaseFormat;

import model.daoNovo.util.NotDB;

public class AutoTable extends Table {
	
	public AutoTable(Class<?> clazz) {

		this.name = (CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, clazz.getSimpleName()));
		System.out.println("table:" + this.name);

		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			Annotation[] annotations = f.getDeclaredAnnotations();
			if(hasExcudedAnnotation(annotations)) {
				continue;
			}
			System.out.println(f.getName() + ":" + f.getGenericType().getTypeName());
			String columName = (CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, f.getName()));
			String[] split = f.getGenericType().getTypeName().split("\\.");
			String columType = split[split.length-1];
			colums.add(new Colum(columType,columName,this ));
		}

		/*
		 * BeanInfo info = Introspector.getBeanInfo(model.getClass());
		 * PropertyDescriptor[] pdArray = info.getPropertyDescriptors(); for (int count
		 * =0;pdArray.length>count;count++ ) { PropertyDescriptor pd = pdArray[count];
		 * System.out.println(pd.getName()+"/tipe:"+pd.getPropertyType().getName()
		 * +"/value: "+pd.getReadMethod().invoke(model)); }
		 */
	}
	static public boolean hasExcudedAnnotation(Annotation[] annotations) {
		if(annotations!=null) {
		for (Annotation a : annotations) {
			System.out.println(a.annotationType() + ":" + a.getClass().getName());
			if (a instanceof NotDB) {
				return true;
			}
		}
		}
		return false;
	}

}
