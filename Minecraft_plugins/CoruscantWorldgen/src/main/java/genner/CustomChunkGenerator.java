package genner;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.generator.ChunkGenerator;


public class CustomChunkGenerator extends ChunkGenerator {
    int floorlevel=100;
    int cflrdata=0;
    int rcx=0;
    int rcz=0;
    int adirx=0; //0 is disabled, 1 is x+, 2 is x- same to the z
    int adirz=0; //whether it needs to generate something direction specific(walls)
    int building_type=0;
    int building_height=0;
    int floorheight=5;
    int flrdposx=0;
    int flrdposz=0;
    List<Material> mlist=Arrays.asList(Material.POLISHED_ANDESITE,Material.POLISHED_ANDESITE,Material.STONE,Material.POLISHED_ANDESITE);
    List<Material> downmatlist=Arrays.asList(Material.POLISHED_ANDESITE,Material.SMOOTH_STONE,Material.SEA_LANTERN);
    List<Material> bmat1=Arrays.asList(Material.POLISHED_ANDESITE,Material.ANDESITE);

	@Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
    	//new Random(world.getSeed())
    	//TODO use biome for areas
    	//cflrdata: 0 is air, 1 is advert table, 2 is pathway, 3 is building, 4 is bridge
        ChunkData chunk = createChunkData(world);
        cflrdata=0;
        adirx=0;
        adirz=0;
        rcx=Math.abs(chunkX);
        rcz=Math.abs(chunkZ);
        if(rcz>3&&rcx%12==1&&random.nextInt(8)==1) {
        	cflrdata=1;
        } else
        if(rcx%12>1&&rcz%12==1&&random.nextInt(8)==1) {
        	cflrdata=1;
        } else if(rcx%12>2&&rcz%12>0) {
        	if((rcx%12==3&&rcz%12>0)||(rcz%12==1&&rcx%13>2)||(rcx%12==11&&rcz%12>0)||(rcx%12>2&&rcz%12==11)) {
        		cflrdata=2;
        		if(rcx%12==3) {
        			adirz=2;
        		}else if(rcx%12==11) {
        			adirz=1;
        		}
        		if(rcz%12==1) {
        			adirx=2;
        		}else if(rcz%12==11) {
        			adirx=1;
        		}
        	} else {
        		cflrdata=3;
        	}
        }
        if(rcz%12==0&&rcx%12>3&&(rcz+rcx)%4==0) {
        	cflrdata=4;
        }
        if(cflrdata==3) {
        	building_height=4+random.nextInt(20);
        	building_type=random.nextInt(8);
        } else if(cflrdata==2) {
        	building_height=1+random.nextInt(3);
        	building_type=random.nextInt(8);
        	flrdposx=random.nextInt(16);
        	flrdposz=random.nextInt(16);
        }
        for (int X = 0; X < 16; X++) {
        	if(rcx%12<=3&&(rcz+rcx)%4!=0) {
        		if(adirx==2) {
            		if(chunkZ>=0) {
            			chunk.setBlock(X, floorlevel+1, 0, Material.STONE_BRICK_WALL);
            		} else {
            			chunk.setBlock(X, floorlevel+1, 15, Material.STONE_BRICK_WALL);
            		}
            	} else if(adirx==1) {
            		if(chunkZ>=0) {
            			chunk.setBlock(X, floorlevel+1, 15, Material.STONE_BRICK_WALL);
            		} else {
            			chunk.setBlock(X, floorlevel+1, 0, Material.STONE_BRICK_WALL);
            		}
            	}
            	
        	} else if(cflrdata==4) {
        		chunk.setBlock(0, floorlevel+1, X, Material.STONE_SLAB);
        		chunk.setBlock(15, floorlevel+1, X, Material.STONE_SLAB);
        	}
        	if(adirz==2) {
        		if(chunkX>=0) {
        			chunk.setBlock(0, floorlevel+1, X, Material.STONE_BRICK_WALL);
        		} else {
        			chunk.setBlock(15, floorlevel+1, X, Material.STONE_BRICK_WALL);
        		}
        	} else if(adirz==1) {
        		if(chunkX>=0) {
        			chunk.setBlock(15, floorlevel+1, X, Material.STONE_BRICK_WALL);
        		} else {
        			chunk.setBlock(0, floorlevel+1, X, Material.STONE_BRICK_WALL);
        		}
        	}
            for (int Z = 0; Z < 16; Z++) {
            	//if(rcx<6||rcz<6) {
            	//	biome.setBiome(chunkX*16+X,chunkZ*16+Z, Biome.PLAINS);
            	//}
            	if(cflrdata>0){
            		if(cflrdata==1) {
            			chunk.setBlock(X, floorlevel, Z, mlist.get(cflrdata-1));
            		}else if(cflrdata==4) {
            			if(((1+X)%2)==0) {
            				chunk.setBlock(X, floorlevel, Z, Material.BLACKSTONE);
            			} else {
            				chunk.setBlock(X, floorlevel, Z, mlist.get(cflrdata-1));
            			}
            		} else {
            			for(int i=floorlevel;i>floorlevel-2;i--) {
                			chunk.setBlock(X, i, Z, mlist.get(cflrdata-1));
                		}
            			chunk.setBlock(X, floorlevel-2, Z, downmatlist.get((X+Z)%2+1));
            			for(int i=floorlevel-3;i>1;i--) {
                			chunk.setBlock(X, i, Z, downmatlist.get((X+Z)%2));
                		}
            			if(cflrdata==3) {
            				//switch building type
            				switch(building_type) {
            				  case 0:
            					  if(X==0||Z==0||X==15||Z==15) {
                  					for(int i=0;i<=building_height*floorheight;i++) {
                  						if(i%floorheight==0||((X==15||X==0)&&(Z==15||Z==0))) {
                  							chunk.setBlock(X, floorlevel+i, Z, Material.LIGHT_GRAY_CONCRETE);
                  						} else {
                  							if(i%floorheight==4) {
                  								chunk.setBlock(X, floorlevel+i, Z, Material.COBBLESTONE_WALL);
                  							} else if(((Z%2==1&&Z<7)||(Z%2==0&&Z>8))||((X%2==1&&X<7)||(X%2==0&&X>8))){
                  								if(i%floorheight==2) {
                  									chunk.setBlock(X, floorlevel+i, Z, Material.QUARTZ_BLOCK);
                  								}else{
                  									chunk.setBlock(X, floorlevel+i, Z, Material.SMOOTH_STONE);
                  								}
                  							} else if((X!=7&&X!=8&&Z!=7&&Z!=8)||i>floorheight){
                  								chunk.setBlock(X, floorlevel+i, Z, Material.LIGHT_BLUE_STAINED_GLASS);
                  							}
                  						}
                  					}
                  				} else {
                  					for(int i=0;i<=building_height;i++) {
                  						chunk.setBlock(X, floorlevel+i*floorheight, Z, bmat1.get(random.nextInt(2)));
                  					}
                  				}
                  				if(X==1||Z==1||X==14||Z==14){
                  					for(int i=0;i<=building_height;i++) {
                  						chunk.setBlock(X, floorlevel+i*floorheight-1, Z, Material.SEA_LANTERN);
                  					}
                  				}
            				    break;
            				  case 1:
            					  if(X==0||Z==0||X==15||Z==15) {
                    				for(int i=0;i<=building_height*floorheight;i++) {
                    					if(i>floorheight) {
                    						if((X==7||X==8||Z==7||Z==8)){
                    							chunk.setBlock(X, floorlevel+i, Z, Material.SEA_LANTERN);
                    						} else {
			                    				if(((Z%2==1&&Z<7)||(Z%2==0&&Z>8))||((X%2==1&&X<7)||(X%2==0&&X>8))){
			                    					chunk.setBlock(X, floorlevel+i, Z, Material.SMOOTH_STONE);
			                    				} else {
			                    					if (i>=building_height*floorheight) {
			                    						chunk.setBlock(X, floorlevel+i, Z, Material.IRON_BLOCK);
			                    						chunk.setBlock(X, floorlevel+i+1, Z, Material.IRON_BARS);
			                    					} else if (i%2==0) {
			                    						chunk.setBlock(X, floorlevel+i, Z, Material.BLUE_STAINED_GLASS);
			                    					} else {
			                    						chunk.setBlock(X, floorlevel+i, Z, Material.SMOOTH_STONE);
			                    					}
			                    				}
                    						}
                    					} else {
	                    					if((X!=7&&X!=8&&Z!=7&&Z!=8)){
		                    					if(((Z%2==1&&Z<7)||(Z%2==0&&Z>8))||((X%2==1&&X<7)||(X%2==0&&X>8))){
		                    						chunk.setBlock(X, floorlevel+i, Z, Material.STONE_BRICKS);
		                    					} else {
		                    						chunk.setBlock(X, floorlevel+i, Z, Material.QUARTZ_BLOCK);
		                    					}
	                    					}
                    					}
                    				}
            					  }else {
                    					for(int i=0;i<=building_height;i++) {
                      						chunk.setBlock(X, floorlevel+i*floorheight, Z, bmat1.get(random.nextInt(2)));
                      					}
                      				}
            					  break;
            				  case 2:
            					  if(X==0||Z==0||X==15||Z==15) {
                      				for(int i=0;i<=building_height*floorheight;i++) {
                      					if(i>floorheight) {
                      						if((X==7||X==8||Z==7||Z==8)){
                      							chunk.setBlock(X, floorlevel+i, Z, Material.LIGHT_BLUE_STAINED_GLASS);
                      						} else {
	  			                    			if (i>=building_height*floorheight) {
	  			                    				chunk.setBlock(X, floorlevel+i, Z, Material.IRON_BLOCK);
	  			                    				chunk.setBlock(X, floorlevel+i+1, Z, Material.IRON_BARS);
	  			                    			} else if (i%2==0) {
	  			                    				chunk.setBlock(X, floorlevel+i, Z, Material.BLUE_STAINED_GLASS);
	  			                    			} else {
	  			                    				chunk.setBlock(X, floorlevel+i, Z, Material.SMOOTH_STONE);
	  			                    			}
                      						}
                      					} else {
  	                    					if((X!=7&&X!=8&&Z!=7&&Z!=8)){
  		                    					if(((Z%2==1&&Z<7)||(Z%2==0&&Z>8))||((X%2==1&&X<7)||(X%2==0&&X>8))){
  		                    						chunk.setBlock(X, floorlevel+i, Z, Material.POLISHED_DIORITE);
  		                    					} else {
  		                    						chunk.setBlock(X, floorlevel+i, Z, Material.LIGHT_BLUE_STAINED_GLASS);
  		                    					}
  	                    					}
                      					}
                      				}
              					  }else {
	                      				for(int i=0;i<=building_height;i++) {
	                        				chunk.setBlock(X, floorlevel+i*floorheight, Z, bmat1.get(random.nextInt(2)));
	                        			}
	                        		}
            					  break;
            				  	default:
            					  if(X==0||Z==0||X==15||Z==15) {
                    				for(int i=0;i<=building_height*floorheight;i++) {
                    					if(i>floorheight) {
                    						if((X==7||X==8||Z==7||Z==8)){
                    							chunk.setBlock(X, floorlevel+i, Z, Material.BLUE_STAINED_GLASS);
                    						} else {
			                    				if(((Z%2==1&&Z<7)||(Z%2==0&&Z>8))||((X%2==1&&X<7)||(X%2==0&&X>8))){
			                    					chunk.setBlock(X, floorlevel+i, Z, Material.POLISHED_BLACKSTONE);
			                    				} else {
			                    					if (i>=building_height*floorheight) {
			                    						chunk.setBlock(X, floorlevel+i, Z, Material.SMOOTH_STONE);
			                    						chunk.setBlock(X, floorlevel+i+1, Z, Material.SMOOTH_STONE_SLAB);
			                    					} else if(((Z%2==1&&Z<7)||(Z%2==0&&Z>8))||((X%2==1&&X<7)||(X%2==0&&X>8))){
			                    						chunk.setBlock(X, floorlevel+i, Z, Material.BLUE_STAINED_GLASS);
			                    					} else {
			                    						chunk.setBlock(X, floorlevel+i, Z, downmatlist.get(i%2));
			                    					}
			                    				}
                    						}
                    					} else {
	                    					if((X!=7&&X!=8&&Z!=7&&Z!=8)){
		                    					if(((Z%2==1&&Z<7)||(Z%2==0&&Z>8))||((X%2==1&&X<7)||(X%2==0&&X>8))){
		                    						chunk.setBlock(X, floorlevel+i, Z, Material.POLISHED_BLACKSTONE);
		                    					} else {
		                    						chunk.setBlock(X, floorlevel+i, Z, Material.SEA_LANTERN);
		                    					}
	                    					}
                    					}
                    				}
            					  }else {
                    					for(int i=0;i<=building_height;i++) {
                      						chunk.setBlock(X, floorlevel+i*floorheight, Z, bmat1.get(random.nextInt(2)));
                      					}
                      				}
            				}
            				
            				//if(X==1&&Z==1) {
            					//Directional dirBlockData = (Directional) Material.IRON_TRAPDOOR.createBlockData();
            					//dirBlockData.setFacing(BlockFace.EAST);
            					//chunk.setBlock(X, floorlevel+1, Z, dirBlockData);
            				//}
            		
            			}else if(cflrdata==2) {
		            		switch(building_type) {
		            			case 0:
		            				if(X==9&&Z==9) {
		            					chunk.setBlock(X, floorlevel+1, Z, Material.POLISHED_BLACKSTONE);
		            					chunk.setBlock(X, floorlevel+2+building_height*2, Z, Material.SEA_LANTERN);
		            					chunk.setBlock(X, floorlevel+3+building_height*2, Z, Material.IRON_TRAPDOOR);
		            					for(int i=0;i<building_height*2;i++) {
		            						chunk.setBlock(X, floorlevel+2+i, Z, Material.STONE_BRICK_WALL);
		            					}
		            				}
		            				break;
		            			case 1:
		            				if((X+Z)%2==0) {
		            					chunk.setBlock(X, floorlevel, Z, Material.POLISHED_BASALT);
		            				} else {
		            					chunk.setBlock(X, floorlevel+1, Z, Material.RAIL);
		            				}
		            				break;
		            			case 2:
		            				if(X>=flrdposx&&X<flrdposx+building_height&&X<16&&Z>=flrdposz&&Z<flrdposz+building_height&&Z<16) {
		            					chunk.setBlock(X, floorlevel+2, Z, Material.CRAFTING_TABLE);
		            					chunk.setBlock(X, floorlevel+1, Z, Material.CRAFTING_TABLE);
		            				}
		            				break;
		            			case 3:
		            				if(X>=flrdposx&&X<flrdposx+building_height&&X<16&&Z>=flrdposz&&Z<flrdposz+building_height&&Z<16) {
		            					chunk.setBlock(X, floorlevel+3, Z, Material.IRON_TRAPDOOR);
		            					chunk.setBlock(X, floorlevel+2, Z, Material.IRON_BLOCK);
		            					chunk.setBlock(X, floorlevel+1, Z, Material.IRON_BLOCK);
		            				}
		            				break;
		            			case 4:
		            				if(X>=flrdposx&&X<flrdposx+building_height*1.5&&X<16&&Z>=flrdposz&&Z<flrdposz+building_height*1.5&&Z<16&&(X+Z)%3==0) {
		            					chunk.setBlock(X, floorlevel+1, Z, Material.CHISELED_STONE_BRICKS);
		            					chunk.setBlock(X, floorlevel+2, Z, Material.BLACKSTONE_WALL);
		            					chunk.setBlock(X, floorlevel+3, Z, Material.IRON_TRAPDOOR);
		            				}
		            				break;
		            			case 5:
		            				if(adirx==0&&Z%2==0) {
		            					chunk.setBlock(X, floorlevel, Z, Material.NETHER_BRICK_SLAB);
		            				} else if(X%2==0){
		            					chunk.setBlock(X, floorlevel, Z, Material.NETHER_BRICK_SLAB);
		            				}
		            				break;
		            			case 6:
		            				if(flrdposx<flrdposz) {
		            					if(X>=flrdposx&&X<flrdposx+3&&X<16&&Z==flrdposz&&Z<16) {
			            					chunk.setBlock(X, floorlevel+1, Z, Material.POLISHED_GRANITE_STAIRS);
			            				}
		            				} else if(Z>=flrdposz&&Z<flrdposz+3&&Z<16&&X==flrdposz&&X<16) {
		            					chunk.setBlock(X, floorlevel+2, Z, Material.OAK_FENCE);
		            					chunk.setBlock(X, floorlevel+1, Z, Material.OAK_FENCE);
		            					chunk.setBlock(X, floorlevel+3, Z, Material.GREEN_CONCRETE_POWDER);
		            				}
		            				break;
		            			case 7:
		            				if(X>=flrdposx&&X<flrdposx+building_height*1.5&&X<16&&Z>=flrdposz&&Z<flrdposz+building_height*1.5&&Z<16&&(X+Z)%2==0) {
		            					chunk.setBlock(X, floorlevel+2, Z, Material.IRON_BARS);
		            					chunk.setBlock(X, floorlevel+1, Z, Material.IRON_BARS);
		            					chunk.setBlock(X, floorlevel+3, Z, Material.OAK_TRAPDOOR);
		            				}
		            				break;
		            		}
		            	}
            		}
            	}
            	chunk.setBlock(X, 1, Z, Material.COAL_BLOCK);
                chunk.setBlock(X, 0, Z, Material.BEDROCK);
    		}
        }
        return chunk;
    }
    //@Override
    //public List<BlockPopulator> getDefaultPopulators(World world) {
    //	return Arrays.asList((BlockPopulator)new CavePopulator(),(BlockPopulator)new GroundPopulator());
    //}
}