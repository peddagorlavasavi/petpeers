package com.hcl.petpeersapp.converter;

import com.hcl.petpeersapp.domain.IDomain;
import com.hcl.petpeersapp.persistance.entity.IEntity;

public interface IConverter<T extends IDomain,M extends IEntity>{
	M convert(T d);
	T convert(M d);
}
