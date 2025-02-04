// intersection
type cup = { size: string };
type brand = { brandName: string };
type brandCup = cup & brand;

let starbucks: brandCup = { size: 'grande', brandName: 'starbucks' };

console.log(starbucks);
