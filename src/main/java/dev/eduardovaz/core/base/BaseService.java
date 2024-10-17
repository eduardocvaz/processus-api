package dev.eduardovaz.core.base;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
@Service
public abstract class BaseService <T extends BaseModel, R extends BaseRepository<T>> {

    @Autowired
    public R repository;


    @SuppressWarnings("unchecked")
    protected Class<T> getPersistentClass(){
        return (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Exclui registro
     */
    @Transactional
    public void delete(Long id) {
        T entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não encontrado!"));
        repository.delete(entity);
    }

}
