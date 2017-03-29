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
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
@XmlRootElement
public class AuthorDTO implements Serializable {

    private Long id;
    private String nombre;
    private String descripcion;
    private String photo;
    private List<BookDTO> books;

    /**
     * @generated
     */
    public AuthorDTO() {
    }

    /**
     * Crea un objeto AuthorDTO a partir de un objeto AuthorEntity.
     *
     * @param entity Entidad AuthorEntity desde la cual se va a crear el nuevo
     * objeto.
     * @generated
     */
    public AuthorDTO(AuthorEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.descripcion = entity.getDescripcion();
            this.photo = entity.getPhoto();
            this.books = BookDTO.toDTO(entity.getBooks());
        }
    }

    /**
     * Convierte un objeto AuthorDTO a AuthorEntity.
     *
     * @return Nueva objeto AuthorEntity.
     * @generated
     */
    public AuthorEntity toEntity() {
        AuthorEntity entity = new AuthorEntity();
        entity.setId(this.getId());
        entity.setNombre(this.getNombre());
        entity.setDescripcion(this.getDescripcion());
        entity.setPhoto(this.getPhoto());
        entity.setBooks(BookDTO.toEntityList(this.getBooks()));
        return entity;
    }
    
    public static AuthorEntity toEntity(AuthorDTO dto) {
        AuthorEntity entity = new AuthorEntity();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPhoto(dto.getPhoto());
        entity.setBooks(BookDTO.toEntityList(dto.getBooks()));
        return entity;
    }
    
    public static AuthorEntity toSimpleEntity(AuthorDTO dto) {
        AuthorEntity entity = new AuthorEntity();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        return entity;
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
     * Obtiene el atributo descripcion.
     *
     * @return atributo descripcion.
     * @generated
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece el valor del atributo descripcion.
     *
     * @param descripcion nuevo valor del atributo
     * @generated
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
    
    public static List<AuthorDTO> toDTO(List<AuthorEntity> list){
        List<AuthorDTO> response=new ArrayList<>();
        for(AuthorEntity entity:list){
            response.add(new AuthorDTO(entity));
        }
        return response;
    }
    
    public static List<AuthorDTO> toSimpleDTO(List<AuthorEntity> list){
        List<AuthorDTO> response=new ArrayList<>();
        for(AuthorEntity entity:list){
            AuthorDTO author = new AuthorDTO();
            author.setId(entity.getId());
            author.setNombre(entity.getNombre());
            response.add(author);
        }
        return response;
    }
    
    public static List<AuthorEntity> toEntityList(List<AuthorDTO> list){
        List<AuthorEntity> response=new ArrayList<>();
        for(AuthorDTO entity:list){
            response.add(toSimpleEntity(entity));
        }
        return response;
    }
    
    public static List<AuthorEntity> toSimpleEntityList(List<AuthorDTO> list){
        List<AuthorEntity> response=new ArrayList<>();
        for(AuthorDTO entity:list){
            response.add(toSimpleEntity(entity));
        }
        return response;
    }
}
