export class Medicion{
  id: number;
  dictamen: string;
  fecha: Date;
  frecuenciaCardiaca: number;
  presionSanguinea: number;
  nivelEstres: number;
}

export class Tratamiento{
  id: number;
  fecha: Date;
  descripcion: string;
}

export class Diagnostico{
  id: number;
  fecha: Date;
  descripcion: string;
}

export class Consejo{
  id: number;
  fecha: Date;
  tipo: string;
  descripcion: string;
}

export class Medico{
  id: number;
  cedula: number;
  tarjetaProfesional: string;
  nombres: string;
  especialista: boolean;
  apellidos: string;
  sexo: string;

}

export class Examen{
  id: number;
  fecha: Date;
  descripcion: string;
}
