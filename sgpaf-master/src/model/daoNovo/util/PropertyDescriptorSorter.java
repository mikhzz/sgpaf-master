package model.daoNovo.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import model.daoNovo.base.AutoTable;

public class PropertyDescriptorSorter {
	public static List<PropertyDescriptor> getSortedPDList(Class<?> clazz) {
		try {
			PropertyDescriptor[] pdArray = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			List<PropertyDescriptor> sortedPDList = new ArrayList<PropertyDescriptor>();

			Field[] fields = clazz.getDeclaredFields();
			for (int index = 0; index < fields.length; index++) {
				if (AutoTable.hasExcudedAnnotation(fields[index].getDeclaredAnnotations())) {
					continue;
				}
				for (int i = 0; i < pdArray.length; i++) {

					if (pdArray[i].getName().equals(fields[index].getName())) {
						sortedPDList.add(index, pdArray[i]);
						System.out.println(pdArray[i].getName() + ":" + fields[index].getName());
						break;
					}
				}
			}
			return sortedPDList;

		} catch (IntrospectionException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static PropertyDescriptor[] getSortedPDArray(Class<?> clazz) {
			List<PropertyDescriptor> sortedPDList = getSortedPDList( clazz);
			return sortedPDList.toArray(new PropertyDescriptor[sortedPDList.size()]);
	}
}
