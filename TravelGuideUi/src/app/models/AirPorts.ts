export interface IAirports{
    _embedded:{
        locations:[
            {
                code:string;
                name:string;
                description:string;
                coordinates:{
                    latitude:number;
                    longitude:number;
                }
                parent:{
                    code:string;
                    name:string;
                    description:string;
                    coordinates:{
                        latitude:number;
                        longitude:number;
                    }
                    parent:{
                        code:string;
                        name:string;
                        description:string;
                        coordinates:{
                            latitude:number;
                            longitude:number;
                        }
                        parent:{
                            code:string;
                            name:string;
                            description:string;
                            coordinates:{
                                latitude:number;
                                longitude:number;
                            }
                        }
                    }
                }
            }
        ]
    };
    page:{
      size:number;
      totalElements:number;
      totalPages:number;
      number:number;
    };

}
