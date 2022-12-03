<template>
	<div class="grid">
		<div class="col-12">
			<div class="card">
				<h5>DataView</h5>
				<DataView :value="produtos" :layout="layout" :paginator="true" :rows="9" :sortOrder="sortOrder" :sortField="sortField">
					<template #header>
						<div class="grid grid-nogutter">
							<div class="col-6 text-left">
								<Dropdown v-model="sortKey" :options="sortOptions" optionLabel="label" placeholder="Sort By Price" @change="onSortChange($event)"/>
							</div>
							<div class="col-6 text-right">
								<DataViewLayoutOptions v-model="layout" />
							</div>
						</div>
					</template>
					<template #list="slotProps">
						<div class="col-12">
							<div class="flex flex-column md:flex-row align-items-center p-3 w-full">
								<img :src="'images/product/cerveja.jpg'" :alt="Cerveja" class="my-4 md:my-0 w-9 md:w-10rem shadow-2 mr-5" />
								<div class="flex-1 text-center md:text-left">
									<div class="font-bold text-2xl">{{slotProps.data.nome}}</div><br>
									<!--<div class="mb-3">{{slotProps.data.description}}</div>-->
									<Rating :modelValue="slotProps.data.rating" :readonly="true" :cancel="false" class="mb-2"></Rating>
									<div class="flex align-items-center">
										<i class="pi pi-tag mr-2"></i>
										<span class="font-semibold">Marca: {{slotProps.data.marca.nome}}</span>
									</div>

								</div>
								<div class="flex flex-row md:flex-column justify-content-between w-full md:w-auto align-items-center md:align-items-end mt-5 md:mt-0">
									<span class="text-2xl font-semibold mb-2 align-self-center md:align-self-end">R$: {{slotProps.data.valor}}</span>
									<Button icon="pi pi-shopping-cart" label="Add to Cart" class="mb-2"></Button>
									<!--<span :class="'product-badge status-'+slotProps.data.inventoryStatus.toLowerCase()">{{slotProps.data.inventoryStatus}}</span>-->
								</div>
							</div>
						</div>
					</template>

					<template #grid="slotProps">
						<div class="col-12 md:col-4">
							<div class="card m-3 border-1 surface-border">
								<div class="flex align-items-center justify-content-between">
									<div class="flex align-items-center">
										<i class="pi pi-tag mr-2"></i>
										<span class="font-semibold">Marca: {{slotProps.data.marca.nome}}</span>
									</div>
								</div>
								<div class="text-center">
									<img :src="'images/product/cerveja.jpg'" :alt="cerveja" class="w-9 shadow-2 my-3 mx-0"/>
									<div class="text-2xl font-bold">{{slotProps.data.nome}}</div>
					
									<Rating :modelValue="slotProps.data.rating" :readonly="true" :cancel="false"></Rating>
								</div>
								<div class="flex align-items-center justify-content-between">
									<span class="text-2xl font-semibold">${{slotProps.data.valor}}</span>
									<Button icon="pi pi-shopping-cart"></Button>
								</div>
							</div>
						</div>
					</template>
				</DataView>
			</div>
		</div>
	</div>
</template>

<script>
	import axios from 'axios'

	export default {
		data() {
			return {
				produtos: [],
				layout: 'list',
				sortKey: null,
				sortOrder: null,
				sortField: null,
				sortOptions: [
					{label: 'Price High to Low', value: '!price'},
					{label: 'Price Low to High', value: 'price'},
				]
			}
		},
		mounted() {
			this.load();
		},
		methods: {
			onSortChange(event){
				const value = event.value.value;
				const sortValue = event.value;

				if (value.indexOf('!') === 0) {
					this.sortOrder = -1;
					this.sortField = value.substring(1, value.length);
					this.sortKey = sortValue;
				}
				else {
					this.sortOrder = 1;
					this.sortField = value;
					this.sortKey = sortValue;
				}
        },
		load() {
            axios
            .get('http://localhost:8080/produto')
            .then(resp => {
                this.produtos = resp.data
            })
        }
		}
	}
</script>

<style scoped lang="scss">
@import '../assets/demo/badges.scss';
</style>