package uz.crm.crmbackend.service;


import uz.crm.crmbackend.repository.BaseRepository;

public abstract class AbstractService<R extends BaseRepository> implements BaseService{
    public final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }
}




