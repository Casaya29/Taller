const express = require('express');

const Model = require('../model/model');
const router = express.Router()

// Isertar familia: POST
router.post('/postfamilia',async(req, res) => {
    const data = new Model({
        apellido: req.body.apellido,
        ubicacion: req.body.ubicacion,
        vivienda: req.body.vivienda,
        riesgo: req.body.riesgo
    })

    try{
        const dataToSave = await data.save()
        res.status(200).json({"result": "ok"})
    }
    catch (error){
        res.status(400).json({"message": error.message})
    }
});

// Seleccionar todas las familias (GET)
router.get('/getallfamilia', async(req, res) => {
    try{
        const data = await Model.find()
        res.json(data)
    }
    catch (error){
        res.status(400).json({"message": error.message})
    }
});

// A eleccionar familia (GET:..../api/getfamilia?id=.....)
router.get('/getfamilia', async(req, res) => {
    try{
        const data = await Model.findById(req.query.id)
        res.json(data)
    }
    catch (error){
        res.status(400).json({"message": error.message})
    }
});

// Actualizar familia : PATCH:..../api/getfamilia?id=.....
router.patch('/updatefamilia', async(req, res) => {
    try{
        const id = req.query.id;
        const updatedData = req.body;
        const options = {new: true}

        const result = await Model.findByIdAndUpdate(
            id, updatedData, options
        )

        res.status(200).json({"result": "ok"})
    }
    catch (error){
        res.status(400).json({"message": error.message})
    }
});

// Eliminar familia DELETE:

router.delete('/deletefamilia', async(req, res) => {
    try{
        const id = req.query.id;

        const result = await Model.findByIdAndDelete(id)
        res.status(200).json({"result": "ok"})
    }
    catch (error){
        res.status(400).json({"message": error.message})
    }
});

module.exports = router;