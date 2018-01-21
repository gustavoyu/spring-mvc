package com.sagawalabs.springmvc.services;

import com.sagawalabs.springmvc.domain.Capybara;

import java.util.List;

/**
 * Created by gustavo on 02/01/2018.
 */
public interface CapybaraService {
    List<Capybara> listCapybaras();

    Capybara getCapybaraById(Integer id);

    Capybara saveOrUpdateCapybara(Capybara capybara);

    void deleteCapybara(Integer id);


}
