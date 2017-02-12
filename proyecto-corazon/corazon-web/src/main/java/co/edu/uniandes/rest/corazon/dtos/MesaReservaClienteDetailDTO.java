package co.edu.uniandes.rest.corazon.dtos;

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
//package co.edu.uniandes.rest.restaurantes.dtos;
//
//import co.edu.uniandes.sisteam.restaurantes.entities.MesaEntity;
//
//import javax.xml.bind.annotation.XmlRootElement;
//import uk.co.jemos.podam.common.PodamExclude;
//
//@XmlRootElement
//public class MesaReservaClienteDetailDTO extends MesaDTO {
//
//    @PodamExclude
//    private SucursalDTO sucursal;
//
//    /**
//     *
//     */
//    public MesaReservaClienteDetailDTO() {
//        super();
//    }
//
//    /**
//     * Crea un objeto MesaDetailDTO a partir de un objeto MesaEntity incluyendo
//     * los atributos de MesaDTO.
//     *
//     * @param entity Entidad MesaEntity desde la cual se va a crear el nuevo
//     * objeto.
//     *
//     */
//    public MesaReservaClienteDetailDTO(MesaEntity entity) {
//        super(entity);
//        if (entity.getSucursal() != null) {
//            this.sucursal = new SucursalDetailDTO(entity.getSucursal());
//        }
//    }
//
//    /**
//     * Convierte un objeto MesaDetailDTO a MesaEntity incluyendo los atributos
//     * de MesaDTO.
//     *
//     * @return objeto MesaEntity.
//     *
//     */
//    @Override
//    public MesaEntity toEntity() {
//        MesaEntity entity = super.toEntity();
//        if (this.getSucursal() != null) {
//            entity.setSucursal(this.getSucursal().toEntity());
//        }
//        return entity;
//    }
//
//    /**
//     * Obtiene el atributo sucursal.
//     *
//     * @return atributo sucursal.
//     *
//     */
//    public SucursalDTO getSucursal() {
//        return sucursal;
//    }
//
//    /**
//     * Establece el valor del atributo sucursal.
//     *
//     * @param sucursal nuevo valor del atributo
//     *
//     */
//    public void setSucursal(SucursalDTO sucursal) {
//        this.sucursal = sucursal;
//    }
//    
//    
//
//}
