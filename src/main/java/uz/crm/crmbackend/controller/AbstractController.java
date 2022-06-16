package uz.crm.crmbackend.controller;


import uz.crm.crmbackend.service.BaseService;

public abstract class AbstractController <S extends BaseService>{
public final S service;
public AbstractController(S service){this.service = service;}
}
