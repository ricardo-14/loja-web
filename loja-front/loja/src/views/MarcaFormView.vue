<template>
<Toast/>
<div class="corpo">
    <div class="container">
        <div class="header">
            <h2>Nova Marca</h2>
        </div>

        <form id="form" class="form" @submit.prevent="salvar()">
            <div class="form-control">
                <label for="nome">Nome da Marca: </label>
                <InputText type="text" v-model="marca.nome"/>
            </div>

            <Button type="submit" label="Salvar"/>
            <Button label="Voltar" class="p-button-link" @click="voltar()"/>
        </form>
    </div>
</div>
</template>

<script>
import axios from 'axios'
export default {
    data() {
        return {
            marca: {}
        }
    },
    mounted() {
        const id = this.$route.params.id
        if (id) {
            axios.get(`http://localhost:8080/marca/${id}`).then(resp => this.marca = resp.data)
            .catch(error => {
                alert(error)
                this.$router.push("/marca")
            })
        }
        else {
            this.marca = {}
        }
    },
    methods: {
        salvar() {
            axios.post('http://localhost:8080/marca', this.marca)
            .then(() => this.$toast.add({severity:'success', summary: 'Successo!', detail: 'Registro gravado.', life: 4000}))
            .catch(error => this.$toast.add({severity:'error', summary: `Problema na gravação ${error}`}))
            this.limpaForm()
        },
        limpaForm() {

            this.marca = {
                nome: ''
            }

        },
        voltar() {
            this.$router.go(-1)
        }
    }
}
</script>

<style>
.corpo {
    width: 100%;
    min-height: 90vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

.container {
  background-color: #fafafa;
  border-radius: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
  width: 400px;
  max-width: 100%;
  overflow: hidden;
}

.header {
  background-color: #eee;
  padding: 20px;
}

.form {
  padding: 20px;
}

.form-control {
  margin-bottom: 10px;
  padding-bottom: 20px;
  position: relative;
}

.form-control label {
  display: inline-block;
  margin-bottom: 10px;
}

.form .p-inputtext {
  border: 2px solid #f0f0f0;
  display: block;
  border-radius: 10px;
  font-size: 14px;
  width: 100%;
  padding: 10px;
}

.form .p-button {
  font-size: 14px;
  width: 100%;
  border-radius: 10px;
  padding: 10px;
  margin-bottom: 5px;
}

</style>