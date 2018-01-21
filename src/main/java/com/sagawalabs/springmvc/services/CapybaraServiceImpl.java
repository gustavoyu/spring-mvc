package com.sagawalabs.springmvc.services;

import com.sagawalabs.springmvc.domain.Capybara;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by gustavo on 02/01/2018.
 */
@Service
public class CapybaraServiceImpl implements CapybaraService {

    private Map<Integer, Capybara> capybaraMap;

    CapybaraServiceImpl() {
        loadCapybaras();
    }

    private void loadCapybaras() {
        capybaraMap = new HashMap();

        Capybara capybara1 = new Capybara();
        capybara1.setId(1);
        capybara1.setName("Gilberto");
        capybara1.setCar("Honda City");
        capybara1.setImageUrl("http://www.google.com/q=Honda City");
        capybaraMap.put(1, capybara1);

        Capybara capybara2 = new Capybara();
        capybara2.setId(2);
        capybara2.setName("Guillermo");
        capybara2.setCar("Gol");
        capybara2.setImageUrl("http://www.google.com/q=gol");
        capybaraMap.put(2, capybara2);

        Capybara capybara3 = new Capybara();
        capybara3.setId(3);
        capybara3.setName("Will I Am");
        capybara3.setCar("Uno");
        capybara3.setImageUrl("http://www.google.com/q=uno");
        capybaraMap.put(3, capybara3);

        Capybara capybara4 = new Capybara();
        capybara4.setId(4);
        capybara4.setName("Everton");
        capybara4.setCar("Gol");
        capybara4.setImageUrl("http://www.google.com/q=gol");
        capybaraMap.put(4, capybara4);
    }

    @Override
    public List<Capybara> listCapybaras() {
        return new ArrayList<>(capybaraMap.values());
    }

    @Override
    public Capybara getCapybaraById(Integer id) {
        return capybaraMap.get(id);
    }

    @Override
    public Capybara saveOrUpdateCapybara(Capybara capybara) {
        if(capybara != null){
            if(capybara.getId() == null){
                capybara.setId(Collections.max(capybaraMap.keySet())+1);
            }
            capybaraMap.put(capybara.getId(), capybara);
        }
        return capybara;
    }

    @Override
    public void deleteCapybara(Integer id) {
        capybaraMap.remove(id);
    }
}
