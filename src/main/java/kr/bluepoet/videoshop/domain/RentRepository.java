package kr.bluepoet.videoshop.domain;

import java.util.List;

/**
 * Created by bluepoet on 2017. 10. 7..
 */
public interface RentRepository {
    Rent findById(long id);
    List<Rent> findByRenter(String name);
}
