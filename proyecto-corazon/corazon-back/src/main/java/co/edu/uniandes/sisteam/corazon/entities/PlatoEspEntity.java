package co.edu.uniandes.sisteam.corazon.entities;

///*
//The MIT License (MIT)
//
//Copyright (c) 2015 Los Andes University
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.
// */
//package co.edu.uniandes.sisteam.restaurantes.entities;
//
//import java.io.Serializable;
//import javax.persistence.Entity;
//import uk.co.jemos.podam.common.PodamExclude;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import java.util.List;
//import java.util.ArrayList;
//
//@Entity
//public class PlatoEspEntity extends BaseEntity implements Serializable {
//
//    @PodamExclude
//    @ManyToOne
//    private SucursalEntity sucursal;
//
//    private String nombre;
//    private int precio;
//    private String descripcion;
//    
//    /**
//     * Obtiene el atributo sucursal.
//     *
//     * @return atributo sucursal.
//     *
//     */
//    public SucursalEntity getSucursal() 
//    {
//        return sucursal;
//    }
//
//    /**
//     * Establece el valor del atributo sucursal.
//     *
//     * @param sucursal nuevo valor del atributo
//     *
//     */
//    public void setSucursal(SucursalEntity sucursal) {
//        this.sucursal = sucursal;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public int getPrecio() {
//        return precio;
//    }
//
//    public void setPrecio(int precio) {
//        this.precio = precio;
//    }
//
//    public String getDescripcion() {
//        return descripcion;
//    }
//
//    public void setDescripcion(String descripcion) {
//        this.descripcion = descripcion;
//    }
//
//    
//    
//}
