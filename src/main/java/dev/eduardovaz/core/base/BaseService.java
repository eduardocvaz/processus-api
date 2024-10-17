package dev.eduardovaz.core.base;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service
public abstract class BaseService <T extends BaseModel, R extends BaseRepository<T>> {

    public R repository;


    @SuppressWarnings("unchecked")
    protected Class<T> getPersistentClass(){
        return (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Retorna lista de objetos
     */
    public List<T> findAll() {
        return repository.findAll();
    }


    /**
     * Retorna lista de objetos de forma paginada
     */
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Retorna um objeto de acordo com o seu id
     * @param id Id do objeto
     */
    public T findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item com id: " + id + " não foi encontrado."));
    }

    /**
     * Adiciona novo registro
     */
    @Transactional
    public T insert(T model) {
        return repository.save(model);
    }

    /**
     * Atualiza registro
     */
    @Transactional
    public T update(T model) {
        repository.findById(model.getId()).orElseThrow(() -> new EntityNotFoundException("Não encontrado!"));
        return repository.save(model);
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
