import { Component } from '@angular/core';
import { GlobalService } from './services/global.service';
import { cubo, config } from './app.interface';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  //controles de formularios y botones
  formOptions:boolean = false;
  formConfig:boolean = false;
  formConsult:boolean = false;
  messageAlert:boolean = false;
  btnEjecutar:boolean = false;
  tableConfig :boolean= false;
  submit :boolean= true;
  message:string = ''; 
  disablebtnConfig:boolean = false;
  disablebtnOper:boolean = false;
  tablemanual = false;
  tableauto = false;


  //datos
  listaConfiguraciones:any[] =[];
  listaOperaciones:any[] =[];
  listaConsultalabel:any[] =[
    { id: "",
      longitud: "",
      operaciones: ""
    }
  ]
  opciontest:number = 1;
  cubo: cubo ={
    cpb: 0,
    tes: '',
    dat:[]
    };  
    config: config = {
      conf:'',
      consul:[]
    }
    x:number =0; 
    y:number =0; 
    z:number =0; 
    w:number =0; 
    x1:number =0; 
    y1:number =0; 
    z1:number =0; 
    tipo_consulta:string;
    listaresp:any[]=[];

  //temporales
  num_test:number;
  num_cubo:number;
  num_consultas:number;
  contador_test:number = 0;  
  total_operaciones:number = 0;
  cont_operaciones:number =1;
  cont_config:number =0;
  label_operaciones:number;
  label_config:number;

  constructor(private req: GlobalService) {
    this.listaConsultalabel =[];
  }


  fnchangecheck(event): void{  
    this.opciontest = event;
  }

  viewFormoptions(){

    this.opciontest == 2 ? this.formOptions = true : this.ejecutarAuto();
    this.opciontest == 1 && (this.formOptions = false);
     this.cubo.tes = this.opciontest.toString(); //agregando opcion de test automatica o manual
    this.btnEjecutar = true;
    this.tablemanual = false;
    this.tableauto = false;
    this.listaresp = [];  
    
  }

  hideFormoptions(){ //ocultar formulario de numero de test
    this.formOptions = false;
    this.contador_test= 0;
    this.num_cubo = null;
    this.num_consultas = null;
    this.num_test = null;
    this.listaConfiguraciones =[];
    this.listaConsultalabel =[];
    this.disablebtnConfig = false;
    this.tableConfig = false;
    this.btnEjecutar = false;
  }

  //traer información automática por medio del servicio de los test
  ejecutarAuto(){

    this.cubo.tes = "1";
    this.req.getData(this.cubo).subscribe(res => {

       this.tableauto = true;
       this.listaresp = res['data'];    
       console.log(this.listaresp);
       
        this.btnEjecutar = false;        
    })   
  }

  //agregar configuracion desde el modal
  fnagregarConfig(){
    this.tableConfig = true;
    this.cubo.cpb =  this.num_test;
    let configTemp = `${this.num_cubo} ${this.num_consultas}`;
    this.listaConfiguraciones.push(configTemp);
    this.listaConsultalabel.push({
      id: this.contador_test,
      longitud: ` ${this.num_cubo} x  ${this.num_cubo} x ${this.num_cubo}`,
      operaciones: this.num_consultas
    });

    this.contador_test ++;
    this.total_operaciones += this.num_consultas;
    this.num_cubo = null;
    this.num_consultas = null;
    
    if( this.contador_test == this.num_test)  this.disablebtnConfig = true;
  }

  fndeleteconfig(index:any){

    console.log(index);
    
    let posicion = this.listaConfiguraciones.indexOf(index);
    if (posicion > -1) {
      this.listaConfiguraciones.splice(posicion, 1);
      this.listaConsultalabel.splice(posicion, 1);
   }
   this.total_operaciones - 1;
  }



  //remover desde el modal PENDIENTE

  //guardar configuración desde el modal
  fnsaveConfig(){
 
   this.formConsult = true;
   this.formOptions = false;
   this.label_operaciones = 1;
   this.label_config = 1;

  }

 //cancelar desde el modal  
  fncleanModal(){
    this.contador_test= 0;
    this.num_cubo = null;
    this.num_consultas = null;
    this.num_test = null;
    this.listaConfiguraciones =[];
    this.listaConsultalabel=[];
    this.disablebtnConfig = false;
    this.tableConfig = false;
    this.total_operaciones = 0;
  }

  fnagregarOperaciones(){

    let configuraciones;
    let valoresOpera = '';
    let operaciones:number =0;

    configuraciones = this.listaConfiguraciones[this.cont_config];
    this.config.conf = configuraciones;
   // console.log(configuraciones);
    
   operaciones = configuraciones.slice(1).trim();


    if(this.tipo_consulta == 'update') valoresOpera = `${this.tipo_consulta} ${this.x} ${this.y} ${this.z} ${this.w}`;
    if(this.tipo_consulta == 'query') valoresOpera = `${this.tipo_consulta} ${this.x} ${this.y} ${this.z} ${this.x1} ${this.y1} ${this.z1}`;
      
    this.config.consul.push(valoresOpera);

    console.log(this.config.consul);
    

    if(this.cont_operaciones == operaciones ){     
      this.cubo.dat.push( this.config);
      this.label_operaciones = 1;
      this.listaOperaciones = [];
      this.label_config ++;
      

      if(this.cont_config == this.listaConfiguraciones.length-1){
        this.disablebtnOper = true;
        this.cont_config = 0;
        this.cont_operaciones = 0;
      
      }else{
        this.cont_config ++;
      }
    }

    this.cont_operaciones ++;
    this.label_operaciones ++;
    this.x=0;
    this.y=0;
    this.z=0;
    this.w=0;
    this.x1=0;
    this.y1=0;
    this.z1=0;   

  }

  //hacer operaciones
  enviar(){
    this.req.getData(this.cubo).subscribe(res => {
      //  if (res['status'] == 'OK') {

         this.tablemanual = res['data'];         
          this.btnEjecutar = false;
          this.fncancelOperacion();
          
       // }
      })

      
  }

  fncancelOperacion(){
     
    this.formConsult = false;
    this.btnEjecutar = false;
    this.contador_test= 0;
    this.cont_config = 0;
    this.cont_operaciones = 0;
    this.label_operaciones=0;
    this.num_cubo = null;
    this.num_consultas = null;
    this.num_test = null;
    this.listaConfiguraciones =[];
    this.listaConsultalabel=[];
    this.disablebtnConfig = false;
    this.disablebtnOper = false;
    this.tableConfig = false;
    this.total_operaciones = 0;
    this.x=0;
    this.y=0;
    this.z=0;
    this.w=0;
    this.x1=0;
    this.y1=0;
    this.z1=0;   
    
  }


  fnvalidaciones(){
    if( this.num_test > 50 || this.num_test < 0){
      this.messageAlert = true;
   }
  }
  
}
