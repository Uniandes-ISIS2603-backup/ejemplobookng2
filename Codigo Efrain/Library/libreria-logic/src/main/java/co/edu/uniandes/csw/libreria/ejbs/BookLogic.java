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
import co.edu.uniandes.csw.libreria.entities.BookEntity;
import co.edu.uniandes.csw.libreria.persistence.BookPersistence;
import co.edu.uniandes.csw.libreria.entities.ReviewEntity;
import co.edu.uniandes.csw.libreria.api.IReviewLogic;
import co.edu.uniandes.csw.libreria.entities.AuthorEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class BookLogic implements IBookLogic {

    @Inject private BookPersistence persistence;


    @Inject private IReviewLogic reviewLogic;

    /**
     * Obtiene el número de registros de Book.
     *
     * @return Número de registros de Book.
     * @generated
     */
    public int countBooks() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Book.
     *
     * @return Colección de objetos de BookEntity.
     * @generated
     */
    @Override
    public List<BookEntity> getBooks() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de Book indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de BookEntity.
     * @generated
     */
    @Override
    public List<BookEntity> getBooks(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de BookEntity con los datos del Book consultado.
     * @generated
     */
    public BookEntity getBook(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Book en la base de datos.
     *
     * @param entity Objeto de BookEntity con los datos nuevos
     * @return Objeto de BookEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public BookEntity createBook(BookEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Book.
     *
     * @param entity Instancia de BookEntity con los nuevos datos.
     * @return Instancia de BookEntity con los datos actualizados.
     * @generated
     */
    @Override
    public BookEntity updateBook(BookEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteBook(Long id) {
        persistence.delete(id);
    }
  

    /**
     * Obtiene una colección de instancias de ReviewEntity asociadas a una
     * instancia de Book
     *
     * @param bookId Identificador de la instancia de Book
     * @return Colección de instancias de ReviewEntity asociadas a la instancia de Book
     * @generated
     */
    @Override
    public List<ReviewEntity> listReview(Long bookId) {
        return getBook(bookId).getReview();
    }

    /**
     * Obtiene una instancia de ReviewEntity asociada a una instancia de Book
     *
     * @param bookId Identificador de la instancia de Book
     * @param reviewId Identificador de la instancia de Review
     * @generated
     */
    @Override
    public ReviewEntity getReview(Long bookId, Long reviewId) {
        List<ReviewEntity> list = getBook(bookId).getReview();
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setId(reviewId);
        int index = list.indexOf(reviewEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Review existente a un Book
     *
     * @param bookId Identificador de la instancia de Book
     * @param reviewId Identificador de la instancia de Review
     * @return Instancia de ReviewEntity que fue asociada a Book
     * @generated
     */
    @Override
    public ReviewEntity addReview(Long bookId, Long reviewId) {
        BookEntity bookEntity = getBook(bookId);
        ReviewEntity reviewEntity = reviewLogic.getReview(reviewId);
        reviewEntity.setBook(bookEntity);
        return reviewEntity;
    }

    /**
     * Remplaza las instancias de Review asociadas a una instancia de Book
     *
     * @param bookId Identificador de la instancia de Book
     * @param list Colección de instancias de ReviewEntity a asociar a instancia de Book
     * @return Nueva colección de ReviewEntity asociada a la instancia de Book
     * @generated
     */
    @Override
    public List<ReviewEntity> replaceReview(Long bookId, List<ReviewEntity> list) {
        BookEntity bookEntity = getBook(bookId);
        List<ReviewEntity> reviewList = reviewLogic.getReviews();
        for (ReviewEntity review : reviewList) {
            if (list.contains(review)) {
                review.setBook(bookEntity);
            } else {
                if (review.getBook() != null && review.getBook().equals(bookEntity)) {
                    review.setBook(null);
                }
            }
        }
        bookEntity.setReview(list);
        return bookEntity.getReview();
    }

    /**
     * Desasocia un Review existente de un Book existente
     *
     * @param bookId Identificador de la instancia de Book
     * @param reviewId Identificador de la instancia de Review
     * @generated
     */
    @Override
    public void removeReview(Long bookId, Long reviewId) {
        ReviewEntity entity = reviewLogic.getReview(reviewId);
        entity.setBook(null);
    }
}
