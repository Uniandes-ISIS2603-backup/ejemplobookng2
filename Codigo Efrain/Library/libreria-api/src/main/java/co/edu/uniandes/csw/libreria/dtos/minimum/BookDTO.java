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
package co.edu.uniandes.csw.libreria.dtos.minimum;

import co.edu.uniandes.csw.libreria.entities.AuthorEntity;
import co.edu.uniandes.csw.libreria.entities.BookEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
@XmlRootElement
public class BookDTO  implements Serializable{

    private Long id;
    private String nombre;
    private String foto;
    private List<AuthorDTO> author;


    /**
     * @generated
     */
    public BookDTO() {
    }

    /**
     * Crea un objeto BookDTO a partir de un objeto BookEntity.
     *
     * @param entity Entidad BookEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public BookDTO(BookEntity entity) {
        if (entity!=null){
            this.id=entity.getId();
            this.nombre=entity.getNombre();
            this.foto=entity.getFoto();
            this.author = this.returnSimpleDTOList(entity.getAuthor());
       }
    }

    /**
     * Convierte un objeto BookDTO a BookEntity.
     *
     * @return Nueva objeto BookEntity.
     * @generated
     */
    public BookEntity toEntity() {
        BookEntity entity = new BookEntity();
        entity.setId(this.getId());
        entity.setNombre(this.getNombre());
        entity.setFoto(this.getFoto());
        entity.setAuthor(returnSimpleAuthorList(this.getAuthor()));
        return entity;
    }
    
    public static BookEntity toEntity(BookDTO dto) {
        BookEntity entity = new BookEntity();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setFoto(dto.getFoto());
        entity.setAuthor(returnSimpleAuthorList(dto.getAuthor()));
        return entity;
    }
    
    public static List<AuthorEntity> returnSimpleAuthorList(List<AuthorDTO> authors){
        List<AuthorEntity> authorsEntities = new ArrayList<>();
        for(AuthorDTO author:authors){
            AuthorEntity ae = new AuthorEntity();
            ae.setId(author.getId());
            ae.setNombre(author.getNombre());
            authorsEntities.add(ae);
        }
        
        return authorsEntities;
    }
    
    public static List<AuthorDTO> returnSimpleDTOList(List<AuthorEntity> authors){
        List<AuthorDTO> authorsDTOs = new ArrayList<>();
        for(AuthorEntity author:authors){
            AuthorDTO ae = new AuthorDTO();
            ae.setId(author.getId());
            ae.setNombre(author.getNombre());
            authorsDTOs.add(ae);
        }
        
        return authorsDTOs;
    }

    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el atributo nombre.
     *
     * @return atributo nombre.
     * @generated
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el valor del atributo nombre.
     *
     * @param nombre nuevo valor del atributo
     * @generated
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el atributo foto.
     *
     * @return atributo foto.
     * @generated
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Establece el valor del atributo foto.
     *
     * @param foto nuevo valor del atributo
     * @generated
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public static List<BookDTO> toDTO(List<BookEntity> list){
        List<BookDTO> response=new ArrayList<>();
        for(BookEntity entity:list){
            response.add(new BookDTO(entity));
        }
        return response;
    }
    
    public static List<BookEntity> toEntityList(List<BookDTO> list){
        List<BookEntity> response=new ArrayList<>();
        for(BookDTO entity:list){
            response.add(toEntity(entity));
        }
        return response;
    }

    public List<AuthorDTO> getAuthor() {
        return author;
    }

    public void setAuthor(List<AuthorDTO> author) {
        this.author = author;
    }
}
