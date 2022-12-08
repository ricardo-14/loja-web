<template>
  <div class="grid">
		<div class="col-12">
			<div class="card">
				<Toast/>
				<DataTable ref="dt" :value="carrinho" dataKey="id" :paginator="true" :rows="10" :filters="filters"
							paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown" :rowsPerPageOptions="[5,10,25]"
							responsiveLayout="scroll">
					<template #header>
						<div class="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
							<h5 class="m-0">Produtos no carrinho</h5>
						</div>
					</template>

					<Column field="id" header="Id" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
          <Column field="quantidade" header="Quantidade" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
          <Column field="valorT" header="Valor Total" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
          <Column field="produto.nome" header="Produto" :sortable="true" headerStyle="width:14%; min-width:10rem;"></Column>
					
					<Column headerStyle="min-width:10rem;">
						<template #body="{data}">
							<!--<Button icon="pi pi-pencil" class="p-button-rounded p-button-warning mr-2" @click="editar(data)" />-->
							<Button icon="pi pi-trash" class="p-button-rounded p-button-danger mt-2" @click="excluir(data)" />
						</template>
					</Column>
				</DataTable>
			</div>
		</div>
  </div>
</template>

<script>
import {FilterMatchMode} from 'primevue/api';
import axios from 'axios'

export default {
	data() {
		return {
            carrinho: [],
			filters: {},
		}
	},
	created() {
		this.initFilters();
	},
	methods: {
        load() {
            axios
            .get('http://localhost:8080/carrinho')
            .then(resp => {
                this.carrinho = resp.data
            })
        },	
      excluir(carrinho) {
			this.deleteProductDialog = false;
            const id = carrinho.id
            axios
                .delete(`http://localhost:8080/carrinho/${id}`)
                .then(this.load(),
				location.reload())
                .catch(error => alert(error))
                
        },
		initFilters() {
            this.filters = {
                'global': {value: null, matchMode: FilterMatchMode.CONTAINS},
            }
        }
	},
	mounted() {
		this.load();
	}
}
</script>

<style scoped lang="scss">
@import '../assets/demo/badges.scss';
</style>