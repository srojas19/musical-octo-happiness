$(function () {

    Morris.Line({
        element: 'morris-area-chart',
        data: [{
                period: '2010 Q1',
                FecuenciaCardiaca: 2666,
                NivelEstres: null,
                PresionSanguinea: 2647
            }, {
                period: '2010 Q2',
                FecuenciaCardiaca: 2778,
                NivelEstres: 2294,
                PresionSanguinea: 2441
            }, {
                period: '2010 Q3',
                FecuenciaCardiaca: 4912,
                NivelEstres: 1969,
                PresionSanguinea: 2501
            }, {
                period: '2010 Q4',
                FecuenciaCardiaca: 3767,
                NivelEstres: 3597,
                PresionSanguinea: 5689
            }, {
                period: '2011 Q1',
                FecuenciaCardiaca: 6810,
                NivelEstres: 1914,
                PresionSanguinea: 2293
            }, {
                period: '2011 Q2',
                FecuenciaCardiaca: 5670,
                NivelEstres: 4293,
                PresionSanguinea: 1881
            }, {
                period: '2011 Q3',
                FecuenciaCardiaca: 4820,
                NivelEstres: 3795,
                PresionSanguinea: 1588
            }, {
                period: '2011 Q4',
                FecuenciaCardiaca: 15073,
                NivelEstres: 5967,
                PresionSanguinea: 5175
            }, {
                period: '2012 Q1',
                FecuenciaCardiaca: 10687,
                NivelEstres: 4460,
                PresionSanguinea: 2028
            }, {
                period: '2012 Q2',
                FecuenciaCardiaca: 8432,
                NivelEstres: 5713,
                PresionSanguinea: 1791
            }],
        xkey: 'period',
        ykeys: ['FecuenciaCardiaca', 'NivelEstres', 'PresionSanguinea'],
        labels: ['Fecuencia Cardiaca', 'Nivel estres', 'Presion sanguinea'],
        pointSize: 2,
        hideHover: 'auto',
        resize: true
    });

    Morris.Donut({
        element: 'morris-donut-chart',
        data: [{
                label: "Rojo",
                value: 1
            }, {
                label: "Amarillo",
                value: 5
            }, {
                label: "Verde",
                value: 20
            }],
        colors: [
            'rgba(255, 98, 51, 1)',
            'rgba(255, 249, 51, 1)',
             '#0BA462'
        ],
        resize: true
    });

    Morris.Bar({
        element: 'morris-bar-chart',
        data: [{
                y: '2006',
                a: 100,
                b: 90
            }, {
                y: '2007',
                a: 75,
                b: 65
            }, {
                y: '2008',
                a: 50,
                b: 40
            }, {
                y: '2009',
                a: 75,
                b: 65
            }, {
                y: '2010',
                a: 50,
                b: 40
            }, {
                y: '2011',
                a: 75,
                b: 65
            }, {
                y: '2012',
                a: 100,
                b: 90
            }],
        xkey: 'y',
        ykeys: ['a', 'b'],
        labels: ['Series A', 'Series B'],
        hideHover: 'auto',
        resize: true
    });

});
