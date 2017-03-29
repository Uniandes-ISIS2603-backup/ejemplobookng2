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
package co.edu.uniandes.csw.libreria.resources;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.libreria.api.IBookLogic;
import co.edu.uniandes.csw.libreria.dtos.detail.ReviewDetailDTO;
import co.edu.uniandes.csw.libreria.entities.ReviewEntity;
import java.util.ArrayList;
/**
 * URI: books/{booksId: \\d+}/review
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookReviewResource {

    @Inject private IBookLogic bookLogic;
    @Context private HttpServletResponse response;

    /**
     * Convierte una lista de ReviewEntity a una lista de ReviewDetailDTO.
     *
     * @param entityList Lista de ReviewEntity a convertir.
     * @return Lista de ReviewDetailDTO convertida.
     * @generated
     */
    private List<ReviewDetailDTO> reviewListEntity2DTO(List<ReviewEntity> entityList){
        List<ReviewDetailDTO> list = new ArrayList<>();
        for (ReviewEntity entity : entityList) {
            list.add(new ReviewDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de ReviewDetailDTO a una lista de ReviewEntity.
     *
     * @param dtos Lista de ReviewDetailDTO a convertir.
     * @return Lista de ReviewEntity convertida.
     * @generated
     */
    private List<ReviewEntity> reviewListDTO2Entity(List<ReviewDetailDTO> dtos){
        List<ReviewEntity> list = new ArrayList<>();
        for (ReviewDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Obtiene una colección de instancias de ReviewDetailDTO asociadas a una
     * instancia de Book
     *
     * @param booksId Identificador de la instancia de Book
     * @return Colección de instancias de ReviewDetailDTO asociadas a la instancia de Book
     * @generated
     */
    @GET
    public List<ReviewDetailDTO> listReview(@PathParam("booksId") Long booksId) {
        return reviewListEntity2DTO(bookLogic.listReview(booksId));
    }

    /**
     * Obtiene una instancia de Review asociada a una instancia de Book
     *
     * @param booksId Identificador de la instancia de Book
     * @param reviewId Identificador de la instancia de Review
     * @generated
     */
    @GET
    @Path("{reviewId: \\d+}")
    public ReviewDetailDTO getReview(@PathParam("booksId") Long booksId, @PathParam("reviewId") Long reviewId) {
        return new ReviewDetailDTO(bookLogic.getReview(booksId, reviewId));
    }

    /**
     * Asocia un Review existente a un Book
     *
     * @param booksId Identificador de la instancia de Book
     * @param reviewId Identificador de la instancia de Review
     * @return Instancia de ReviewDetailDTO que fue asociada a Book
     * @generated
     */
    @POST
    @Path("{reviewId: \\d+}")
    public ReviewDetailDTO addReview(@PathParam("booksId") Long booksId, @PathParam("reviewId") Long reviewId) {
        return new ReviewDetailDTO(bookLogic.addReview(booksId, reviewId));
    }

    /**
     * Remplaza las instancias de Review asociadas a una instancia de Book
     *
     * @param booksId Identificador de la instancia de Book
     * @param reviews Colección de instancias de ReviewDTO a asociar a instancia de Book
     * @return Nueva colección de ReviewDTO asociada a la instancia de Book
     * @generated
     */
    @PUT
    public List<ReviewDetailDTO> replaceReview(@PathParam("booksId") Long booksId, List<ReviewDetailDTO> reviews) {
        return reviewListEntity2DTO(bookLogic.replaceReview(booksId, reviewListDTO2Entity(reviews)));
    }

    /**
     * Desasocia un Review existente de un Book existente
     *
     * @param booksId Identificador de la instancia de Book
     * @param reviewId Identificador de la instancia de Review
     * @generated
     */
    @DELETE
    @Path("{reviewId: \\d+}")
    public void removeReview(@PathParam("booksId") Long booksId, @PathParam("reviewId") Long reviewId) {
        bookLogic.removeReview(booksId, reviewId);
    }
}
