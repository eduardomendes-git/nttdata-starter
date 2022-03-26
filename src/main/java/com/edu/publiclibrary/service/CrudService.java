/**
 * 
 */
package com.edu.publiclibrary.service;

import java.util.Set;

/**
 * @author	eduardomendes
 * @date	26 Mar 2022
 *
 */
public interface CrudService<T, ID> {

	Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
