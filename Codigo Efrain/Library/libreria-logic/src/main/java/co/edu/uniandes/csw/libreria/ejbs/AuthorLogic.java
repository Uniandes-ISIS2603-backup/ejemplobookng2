/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.libreria.ejbs;

import co.edu.uniandes.csw.libreria.api.IAuthorLogic;
import co.edu.uniandes.csw.libreria.api.IBookLogic;
import co.edu.uniandes.csw.libreria.entities.AuthorEntity;
import co.edu.uniandes.csw.libreria.entities.BookEntity;
import co.edu.uniandes.csw.libreria.persistence.AuthorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class AuthorLogic implements IAuthorLogic {

    @Inject private AuthorPersistence persistence;
    
    @Inject private IBookLogic bookLogic;


    /**
     * Obtiene el número de registros de Author.
     *
     * @return Número de registros de Author.
     * @generated
     */
    public int countAuthors() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Author.
     *
     * @return Colección de objetos de AuthorEntity.
     * @generated
     */
    @Override
    public List<AuthorEntity> getAuthors() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de Author indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de AuthorEntity.
     * @generated
     */
    @Override
    public List<AuthorEntity> getAuthors(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de Author a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de AuthorEntity con los datos del Author consultado.
     * @generated
     */
    public AuthorEntity getAuthor(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Author en la base de datos.
     *
     * @param entity Objeto de AuthorEntity con los datos nuevos
     * @return Objeto de AuthorEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public AuthorEntity createAuthor(AuthorEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Author.
     *
     * @param entity Instancia de AuthorEntity con los nuevos datos.
     * @return Instancia de AuthorEntity con los datos actualizados.
     * @generated
     */
    @Override
    public AuthorEntity updateAuthor(AuthorEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Author de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteAuthor(Long id) {
        persistence.delete(id);
    }

    @Override
    public BookEntity addBook(Long authorId, Long BookId) {
        AuthorEntity authorEntity = getAuthor(authorId);
        BookEntity bookEntity = bookLogic.getBook(BookId);
        List<BookEntity> books = authorEntity.getBooks();
        books.add(bookEntity);
        authorEntity.setBooks(books);
        persistence.update(authorEntity);
        return bookEntity;
    }

    @Override
    public List<BookEntity> listBook(Long authorId) {
        return getAuthor(authorId).getBooks();
    }

    @Override
    public BookEntity getBook(Long authorId, Long bookId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BookEntity> replaceBook(Long authorId, List<BookEntity> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeBook(Long authorId, Long bookId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
  
        
}
